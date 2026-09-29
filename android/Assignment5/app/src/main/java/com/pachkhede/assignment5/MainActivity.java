package com.pachkhede.assignment5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    EditText etName, etEmail;
    Button btnUpdate, btnFetch;
    ListView listView;

    AppDatabase database;
    UserDao userDao;

    List<User> userList;

    User selectedUser;

    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        btnUpdate = findViewById(R.id.btn_update);
        btnFetch = findViewById(R.id.btn_fetch);
        listView = findViewById(R.id.list_view);

        findViewById(R.id.update_layout).setVisibility(View.GONE);

        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "user_database"
        ).build();

        userDao = database.userDao();
        loadUsersFromDatabase();

        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        apiService = retrofit.create(ApiService.class);

        btnFetch.setOnClickListener(v -> {

            new Thread(() -> {

                int count = userDao.getCount();

                runOnUiThread(() -> {

                    if (count == 0) {
                        fetchUsers();
                    } else {
                        Toast.makeText(
                                MainActivity.this,
                                "Users already exist",
                                Toast.LENGTH_SHORT
                        ).show();
                    }

                });

            }).start();
        });


        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {

                        User user = userList.get(position);

                        deleteUser(user.getId());

                        return true;
                    }
                }
        );

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {

                        selectedUser = userList.get(position);

                        etName.setText(selectedUser.getName());
                        etEmail.setText(selectedUser.getEmail());

                        findViewById(R.id.update_layout)
                                .setVisibility(View.VISIBLE);
                    }
                }
        );

        btnUpdate.setOnClickListener(v -> updateUser());

    }


    private void loadUsersFromDatabase() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                userList = userDao.getAllUsers();
                runOnUiThread(() -> {

                    ArrayAdapter<User> adapter =
                            new ArrayAdapter<>(
                                    MainActivity.this,
                                    android.R.layout.simple_list_item_1,
                                    userList
                            );

                    listView.setAdapter(adapter);
                });
                }

        }).start();
    }



    private void fetchUsers()
    {
        apiService.getUsers().enqueue(
                new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        if(response.isSuccessful() && response.body() != null)
                        {
                            List<User> users = response.body();
                            new Thread(() -> {

                                userDao.insertUsers(users);

                                runOnUiThread(() -> {
                                    loadUsersFromDatabase();
                                });

                            }).start();

                        }
                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable throwable) {

                    }
                }
        );
    }

    private void deleteUser(int id) {

        new Thread(() -> {

            userDao.delete(id);

            runOnUiThread(() -> {

                Toast.makeText(
                        MainActivity.this,
                        "User deleted",
                        Toast.LENGTH_SHORT
                ).show();

                // Reload ListView from Room
                loadUsersFromDatabase();
            });

        }).start();
    }

    private void updateUser() {

        if (selectedUser == null) {
            return;
        }


        String name = etName.getText()
                .toString()
                .trim();

        String email = etEmail.getText()
                .toString()
                .trim();

        if (name.isEmpty() || email.isEmpty()) {

            Toast.makeText(
                    this,
                    "Enter name and email",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        int id = selectedUser.getId();

        new Thread(() -> {

            userDao.updateUser(
                    id,
                    name,
                    email
            );

            runOnUiThread(() -> {

                Toast.makeText(
                        MainActivity.this,
                        "User updated",
                        Toast.LENGTH_SHORT
                ).show();


                findViewById(R.id.update_layout)
                        .setVisibility(View.GONE);

                selectedUser = null;


                loadUsersFromDatabase();
            });

        }).start();
    }


}