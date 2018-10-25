package com.example.flycodeveloper.cevrem;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MekanlarActivity extends AppCompatActivity {

    private RecyclerView rv;
    private String enlem;
    private String boylam;
    private Context context = this;
    private ArrayList<Mekanlar> mekanlarArrayList;
    private  MekanlarRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yerler);

        rv = findViewById(R.id.rv);
        enlem = getIntent().getStringExtra("enlem");
        boylam = getIntent().getStringExtra("boylam");

        Log.e("Enlem",enlem);
        Log.e("Boylam",boylam);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(context));


        mekanGetir();


    }

    void mekanGetir(){
        String key = "AIzaSyDwSgD1EVoX05uMNx1XEz00Yk-mhvcqwIE";
        String aramaCapi = "500";
        String konum = enlem+ "," + boylam;
       // String konum = "41.0352272,28.9775172";
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+konum+"&radius="+aramaCapi+"&key="+key;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("cevap",response);
                try {
                    mekanlarArrayList = new ArrayList<>();

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray mekanlar = jsonObject.getJSONArray("results");
                    for (int i = 0; i<mekanlar.length(); i++) {
                        JSONObject m = mekanlar.getJSONObject(i);

                        String mekan_adi = m.getString("name");
                        String adres = m.getString("vicinity");

                        JSONObject geometry = m.getJSONObject("geometry");
                        JSONObject location = geometry.getJSONObject("location");

                        String enlem = location.getString("lat");
                        String boylam = location.getString("lng");

                        Mekanlar mekan = new Mekanlar(mekan_adi,Double.parseDouble(enlem),Double.parseDouble(boylam),adres);
                        mekanlarArrayList.add(mekan);
                    }
                    adapter = new MekanlarRVAdapter(context,mekanlarArrayList);
                    rv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(context).add(stringRequest);
    }
}
