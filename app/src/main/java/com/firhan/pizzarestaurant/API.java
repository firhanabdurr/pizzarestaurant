package com.firhan.pizzarestaurant;

import java.util.List;
import retrofit2.http.GET;
import retrofit2.Call;

public interface API {
    @GET("uasresto")
    Call<List<PostMethod>> getPosts();
}
