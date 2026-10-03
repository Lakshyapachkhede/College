package com.pachkhede.class6;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    EditText et1;
    ImageView image;
    Toolbar toolbar;
    TextView tv2;
    int PICK_IMAGE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        et1 = findViewById(R.id.et1);
        image = findViewById(R.id.image1);
        tv2 = findViewById(R.id.tv2);

        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    private void generateAIImage(String prompt) {
        tv2.setText("Generating Image");

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stability.ai/")
                .client(client)
                .build();

        HuggingFaceApi api = retrofit.create(HuggingFaceApi.class);

        RequestBody promptBody = RequestBody.create(
                MediaType.parse("text/plain"),
                prompt
        );

        MultipartBody.Part promptPart =
                MultipartBody.Part.createFormData(
                        "prompt",
                        null,
                        promptBody
                );

        RequestBody formatBody = RequestBody.create(
                MediaType.parse("text/plain"),
                "png"
        );

        MultipartBody.Part formatPart =
                MultipartBody.Part.createFormData(
                        "output_format",
                        null,
                        formatBody
                );
        Call<ResponseBody> call = api.generateImage(
                "Bearer sk-D2f0GedDN68SDk0H1v3E88jfkiPU6QX8MMokgjlW4pMGCdbz",
                "image/*",
                promptPart,
                formatPart
        );

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(
                    Call<ResponseBody> call,
                    Response<ResponseBody> response) {

                if (response.isSuccessful() && response.body() != null) {

                    try {

                        byte[] imageBytes = response.body().bytes();

                        Bitmap bitmap = BitmapFactory.decodeByteArray(
                                imageBytes,
                                0,
                                imageBytes.length
                        );

                        image.setImageBitmap(bitmap);
                        tv2.setText("Image Generated successfully");


                    } catch (Exception e) {
                        tv2.setText("Error: " + e.getMessage());

                        Toast.makeText(
                                MainActivity.this,
                                "Error loading image",
                                Toast.LENGTH_SHORT
                        ).show();
                    }

                } else {

                    tv2.setText("Error: " + response.code());

                    Toast.makeText(
                            MainActivity.this,
                            "Image generation failed: " + response.code(),
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(
                    Call<ResponseBody> call,
                    Throwable t) {
                Log.e("STABILITY_ERROR", "API Error", t);
                tv2.setText("Error: " + t.getMessage());
                Toast.makeText(
                        MainActivity.this,
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_1) {


            String prompt = et1.getText().toString();

            if (!prompt.isEmpty()) {
                generateAIImage(prompt);
            }

            return true;
        }

        if (item.getItemId() == R.id.menu_2) {
            Intent intent = new Intent(Intent.ACTION_PICK);

            intent.setType("image/*");

            startActivityForResult(intent, PICK_IMAGE);

            return true;
        }

        if (item.getItemId() == R.id.menu_3) {

            saveImage();

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void saveImage() {

        image.setDrawingCacheEnabled(true);
        Bitmap bitmap = image.getDrawingCache();

        if (bitmap == null) {
            Toast.makeText(this, "No image to save", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();

        values.put(
                MediaStore.Images.Media.DISPLAY_NAME,
                "AI_Image_" + System.currentTimeMillis() + ".png"
        );

        values.put(
                MediaStore.Images.Media.MIME_TYPE,
                "image/png"
        );

        values.put(
                MediaStore.Images.Media.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + "/Assignment6"
        );

        Uri imageUri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values
        );

        if (imageUri != null) {

            try {

                OutputStream outputStream =
                        getContentResolver().openOutputStream(imageUri);

                bitmap.compress(
                        Bitmap.CompressFormat.PNG,
                        100,
                        outputStream
                );

                outputStream.close();

                Toast.makeText(
                        this,
                        "Image saved to Gallery",
                        Toast.LENGTH_SHORT
                ).show();

            } catch (Exception e) {

                Toast.makeText(
                        this,
                        "Error saving image",
                        Toast.LENGTH_SHORT
                ).show();
            }

        } else {

            Toast.makeText(
                    this,
                    "Unable to save image",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE &&
                resultCode == RESULT_OK &&
                data != null) {

            Uri imageUri = data.getData();

            image.setImageURI(imageUri);
        }
    }


}