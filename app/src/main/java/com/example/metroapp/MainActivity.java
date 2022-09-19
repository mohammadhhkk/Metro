package com.example.metroapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.metroapp.adopter.LinesAdopter;
import com.example.metroapp.models.Lines;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler_Line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_Line=findViewById(R.id.recycler_Lines);

        StringRequest request=new StringRequest(Request.Method.GET, "https://androidsupport.ir/pack/metro/getLines.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("","");

                try {

                    List<Lines> linesList=new ArrayList<>();
                    JSONArray array=new JSONArray(response);

                    for (int i=0;i<array.length();i++){

                        JSONObject jsonObject=array.getJSONObject(i);
                        int id=Integer.parseInt(jsonObject.getString("id"));
                        String title=jsonObject.getString("title");
                        String english=jsonObject.getString("EnglishTitle");
                        Log.e("","");


                        Lines lines=new Lines();
                        lines.setId(id);
                        lines.setTitle(title);
                        lines.setEnglishTitle(english);

                        linesList.add(lines);

                    }

                    LinesAdopter linesAdopter=new LinesAdopter(getApplicationContext(),linesList);

                    recycler_Line.setAdapter(linesAdopter);

                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
                    recycler_Line.setLayoutManager(linearLayoutManager);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("","");


            }
        });


        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);





    }
}