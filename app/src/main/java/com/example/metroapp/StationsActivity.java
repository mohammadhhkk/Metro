package com.example.metroapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.metroapp.adopter.StationsAdopter;
import com.example.metroapp.models.Stations;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StationsActivity extends AppCompatActivity {

    Bundle bundle;


    AppCompatTextView Title,EnglishTitle;
    AppCompatImageView img_back;
    RecyclerView recycler_station;

    int id=0;
    String title="";
    String englishTitle="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations);

        Title=findViewById(R.id.txt_Persiantitle);
        EnglishTitle=findViewById(R.id.txt_englishTitle);
        img_back=findViewById(R.id.img_back);
        recycler_station=findViewById(R.id.recycler_station);

        bundle=getIntent().getExtras();

        id=bundle.getInt("id");
        title=bundle.getString("title");
        englishTitle=bundle.getString("english");

        Title.setText(title);
        EnglishTitle.setText(englishTitle);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        StringRequest request=new StringRequest(Request.Method.POST, "https://androidsupport.ir/pack/metro/getStations.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("","");

                List<Stations> stationsList=new ArrayList<>();
                try {
                    JSONArray array=new JSONArray(response);

                    for (int i = 0; i <array.length(); i++) {

                        JSONObject object=array.getJSONObject(i);

                        Stations stations=new Stations();

                        stations.setId(object.getString("id"));
                        stations.setLineId(object.getString("LineId"));
                        stations.setOrderId(object.getString("OrderID"));
                        stations.setStation_duration(object.getString("Station_Duration"));
                        stations.setTitle(object.getString("Title"));
                        stations.setEnglishTitle(object.getString("Title_English"));
                        stations.setCrossLine(object.getString("CrossLine_ID"));
                        stations.setAddress(object.getString("addr"));
                        stations.setTicket(object.getString("ticket"));
                        stations.setEscalator(object.getString("escalator"));
                        stations.setAtm(object.getString("atm"));
                        stations.setTaxi(object.getString("taxi"));
                        stations.setBus(object.getString("bus"));
                        stations.setLost(object.getString("lost"));
                        stations.setParking(object.getString("parking"));
                        stations.setElevator(object.getString("elevator"));
                        stations.setLatitude(object.getString("latitude"));
                        stations.setLongitude(object.getString("longitude"));

                        stationsList.add(stations);
                    }

                    StationsAdopter stationsAdopter=new StationsAdopter(getApplicationContext(),stationsList);
                    recycler_station.setAdapter(stationsAdopter);
                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                    recycler_station.setLayoutManager(linearLayoutManager);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("","");
            }
        }){

            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<>();
                params.put("id",String.valueOf(id));
                return params;
            }
        };

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);


    }
}

