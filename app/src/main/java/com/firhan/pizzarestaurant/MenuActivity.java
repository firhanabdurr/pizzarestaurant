package com.firhan.pizzarestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MenuActivity extends AppCompatActivity {
    TextView receiver_menu_name;
    ImageView menuImages;
    TextView menuDesc;
    TextView menuPrice;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu);
//
//        receiver_menu_name = findViewById(R.id.menu_item_text);
//        // create the get Intent object
//        Intent userIntent = getIntent();
//        // receive the value by getStringExtra() method and
//        // key must be same which is send by first activity
//        String menu_name = userIntent.getStringExtra("menu_name");
//        // display the string into textView
//        receiver_menu_name.setText(menu_name);
//
//        //Change Image
//        menuImages = findViewById(R.id.menu_big_picture);
//        menuDesc = findViewById(R.id.menu_item_desc);
//        menuPrice = findViewById(R.id.menu_item_price);
//
//        if (menu_name.equals("Margherita Pizza")){
//            menuImages.setImageResource(R.drawable.pizza_margherita);
//            menuDesc.setText(R.string.margherita_desc);
//            menuPrice.setText("Rp. 150.000,0");
//        } else if (menu_name.equals("Smoked Salmon Pizza")){
//            menuImages.setImageResource(R.drawable.pizza_smokedsalmon);
//            menuDesc.setText(R.string.salmon_desc);
//            menuPrice.setText("Rp. 120.000,0");
//        } else if (menu_name.equals("Shrimp Pizza")){
//            menuImages.setImageResource(R.drawable.pizza_shrimp);
//            menuDesc.setText(R.string.shrimp_desc);
//            menuPrice.setText("Rp. 170.000,0");
//        } else if (menu_name.equals("Pepperoni Pizza")){
//            menuImages.setImageResource(R.drawable.pizza_pepperoni);
//            menuDesc.setText(R.string.pepperoni_desc);
//            menuPrice.setText("Rp. 110.000,0");
//        }
//    }
//}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        receiver_menu_name = findViewById(R.id.menu_item_text);
        // create the get Intent object
        Intent userIntent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String menu_name = userIntent.getStringExtra("menu_name");
        // display the string into textView
        receiver_menu_name.setText(menu_name);

        //Change Image
        menuImages = findViewById(R.id.menu_big_picture);
        menuDesc = findViewById(R.id.menu_item_desc);
        menuPrice = findViewById(R.id.menu_item_price);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        // URL of the API
        String url = "https://retoolapi.dev/StWODX/uasresto";

        // Request a

        // Request a JSON object response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Get the menu information from the response
                            String menuImageUrl = response.getString("imageUrl");
                            String menuDescription = response.getString("description");
                            String menuPrice = response.getString("price");

                            // Load the image from the URL using Glide
                            Glide.with(MenuActivity.this)
                                    .load(menuImageUrl)
                                    .into(menuImages);

                            // Set the description and price
                            menuDesc.setText(menuDescription);
                            menuPrice.setText(menuPrice);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);