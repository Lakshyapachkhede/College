package com.pachkhede.class6;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface HuggingFaceApi {
    @Multipart
    @POST("/v2beta/stable-image/generate/sd3")
    Call<okhttp3.ResponseBody> generateImage(
            @Header("Authorization") String token,
            @Header("Accept") String accept,
            @Part MultipartBody.Part prompt,
            @Part MultipartBody.Part outputFormat

    );
}
