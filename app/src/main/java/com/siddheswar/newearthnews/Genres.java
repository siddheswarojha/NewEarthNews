package com.siddheswar.newearthnews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Genres extends AppCompatActivity {

    NavigationView navigation;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionbartoggle;
    RecyclerView RecyclerViewGenre;

    String[] author;
    String[] sources;
    String[] links;
    String[] imagelinks;
    String[] title;

    int x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);
        Toast.makeText(this, "Scroll down to load news", Toast.LENGTH_SHORT).show();

        setUptoolBar(); //function for setting the toolbar and navigation drawer.

        RecyclerViewGenre = findViewById(R.id.RecyclerViewGenre);
        RecyclerViewGenre.setLayoutManager(new LinearLayoutManager(this));
        title = new String[10];
        author = new String[10];
        sources = new String[10];
        links = new String[10];
        imagelinks = new String[10];
        RecyclerViewGenre.setAdapter(new RecyclerAdapterGenre(title, author, sources, getApplicationContext(), links, imagelinks));


        navigation = findViewById(R.id.navigaionView);
        /****
         * *Logic for menu items when they are clicked.
         * every intent carries a string and those string are received in the main activity.
         *****/
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_hindi:
                        Intent ihindi = new Intent(Genres.this, MainActivity.class);
                        ihindi.putExtra("value", "hindi");
                        startActivity(ihindi);
                        break;
                    case R.id.nav_headline:
                        Intent itop = new Intent(Genres.this, MainActivity.class);
                        itop.putExtra("value", "top");
                        startActivity(itop);
                        break;
                    case R.id.nav_tech:
                        Intent itech = new Intent(Genres.this, MainActivity.class);
                        itech.putExtra("value", "tech");
                        startActivity(itech);
                        break;
                    case R.id.nav_entertainment:
                        Intent ibollywood = new Intent(Genres.this, MainActivity.class);
                        ibollywood.putExtra("value", "bollywood");
                        startActivity(ibollywood);
                        break;
                    case R.id.nav_global:
                        Intent iglobal = new Intent(Genres.this, MainActivity.class);
                        iglobal.putExtra("value", "global");
                        startActivity(iglobal);
                        break;
                    case R.id.nav_sports:
                        Intent isports = new Intent(Genres.this, MainActivity.class);
                        isports.putExtra("value", "sports");
                        startActivity(isports);
                        break;
                    case R.id.nav_share:
                        Intent ishare = new Intent();
                        ishare.setAction(Intent.ACTION_SEND);
                        ishare.setType("text/plain");
                        startActivity(ishare);

                        break;
                    case R.id.nav_info:
                        Toast.makeText(Genres.this, "Developed by Siddheswar Ojha", Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });
        /**
         *  Volley Request for default news in this activity
         *
         */
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://gnews.io/api/v4/top-headlines?token=5a8ce6e6b54abe627daa221c184ebf05&lang=en", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);
                        title[i] = jo.getString("title");

                        author[i] = "Time : " + jo.getString("publishedAt");
                        JSONObject jo1 = jo.getJSONObject("source");
                        sources[i] = "Sources : " + jo1.getString("name");
                        links[i] = jo.getString("url");
                        imagelinks[i] = jo.getString("image");


                    }

                } catch (JSONException e) {
                    
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Genres.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjectRequest);


    }

    // Setup tool bar function
    private void setUptoolBar() {
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);

        actionbartoggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionbartoggle);
        actionbartoggle.syncState();


    }


}