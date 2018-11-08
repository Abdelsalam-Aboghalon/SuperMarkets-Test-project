package com.example.abdelsalam.supermarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllSuperMarkets extends AppCompatActivity {
    GridView listViewMarket;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    EditText etMarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_super_markets);
        listViewMarket = (GridView) findViewById(R.id.listOfAllSupermarket);
        arrayList = new ArrayList();
        etMarket = (EditText) findViewById(R.id.etMarketName);
        show();

        listViewMarket.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = (String) listViewMarket.getItemAtPosition(position);
                Intent in = new Intent(AllSuperMarkets.this, Supermarket_Products.class);
                in.putExtra("s1", s.toString().trim());
                startActivity(in);


            }
        });
    }


    public void show() {
        String server_url = "http://192.168.43.252/supermarkets_API/showAll_supermarkts.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Json(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        MySingleTon.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void Json(String response) {

        try {
            JSONArray ja = new JSONArray(response);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                String name = jo.getString("Supermarkets_Name");
                //String name= jo.getString("Supermarkets_phone");
                arrayList.add(name + "");
            }
            arrayAdapter = new ArrayAdapter(this, android.R.layout.preference_category, android.R.id.title, arrayList);
            listViewMarket.setAdapter(arrayAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public void searchMarket(View view) {
    }
}