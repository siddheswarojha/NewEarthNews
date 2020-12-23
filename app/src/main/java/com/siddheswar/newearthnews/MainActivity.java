package com.siddheswar.newearthnews;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    String[] author;
    String[] sources;
    String[] links;
    String[] imagelinks;
    String[] title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView Recycler = findViewById(R.id.Recycler);
        Recycler.setLayoutManager(new LinearLayoutManager(this));


        title = new String[10];
        author = new String[10];
        sources = new String[10];
        links = new String[10];
        imagelinks = new String[10];
        Recycler.setAdapter(new RecyclerAdapter(title, author,sources,getApplicationContext(), links, imagelinks));

        /***
         * Intent received from genre activity.
         * then under switch case logic different urls are assigned as per the message received
         */
        Intent i =getIntent();
        String value = i.getStringExtra("value");
        String url = null;

        if (value != null) {
            switch (value)
            {

                case "top" :
                    url = "https://gnews.io/api/v4/top-headlines?token=5a8ce6e6b54abe627daa221c184ebf05&lang=en&country=in";

                    break;
                case "tech" :

                    url = "https://gnews.io/api/v4/top-headlines?token=5a8ce6e6b54abe627daa221c184ebf05&lang=en&country=in&topic=technology";

                    break;
                case "global":

                    url = "https://gnews.io/api/v4/top-headlines?token=5a8ce6e6b54abe627daa221c184ebf05&lang=en&country=us&topic=worldwide";

                    break;
                case "bollywood" :
                    url = "https://gnews.io/api/v4/top-headlines?token=5a8ce6e6b54abe627daa221c184ebf05&lang=en&country=in&topic=entertainment";

                    break;
                case "hindi" :

                    url = "https://gnews.io/api/v4/top-headlines?token=5a8ce6e6b54abe627daa221c184ebf05&lang=hi&country=in&topic=all";

                    break;
                case "sports" :
                    url = "https://gnews.io/api/v4/top-headlines?token=5a8ce6e6b54abe627daa221c184ebf05&lang=en&country=in&topic=sports";

                    break;
                default : Toast.makeText(MainActivity.this,"error",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(this, "Something Unusual occured", Toast.LENGTH_SHORT).show();
        }
/**

 * Volley request formed for fetching the API and displaying it to the user.
 */

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("articles");

                    for(int i=0; i<jsonArray.length(); i++)
                    {
                        JSONObject jo = jsonArray.getJSONObject(i);
                        title[i]=jo.getString("title");

                        author[i]="Time : "+ jo.getString("publishedAt");
                        JSONObject jo1 = jo.getJSONObject("source");
                        sources[i]="Sources : " + jo1.getString("name");
                        links[i]=jo.getString("url");
                        imagelinks[i]=jo.getString("image");


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }




        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjectRequest);



    }
}