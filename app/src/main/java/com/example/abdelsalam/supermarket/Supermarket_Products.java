package com.example.abdelsalam.supermarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Supermarket_Products extends AppCompatActivity {
    GridView listView ;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter ;
    EditText etProd;
    TextView superNameTitle;
    Intent getSuperName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermarket__products);
        listView = (GridView)findViewById(R.id.listOfSuperProduct);
        arrayList = new ArrayList();
        etProd=(EditText)findViewById(R.id.etSuperProductName);
        superNameTitle=(TextView)findViewById(R.id.superNameTitle);
         getSuperName=getIntent();
         superNameTitle.setText(getSuperName.getStringExtra("s1").toString().trim()+" Supermarket");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String p1 = (String) listView.getItemAtPosition(position);
                String s1 = getSuperName.getStringExtra("s1").toString().trim();

                Intent in = new Intent(Supermarket_Products.this, ProductContent.class);
                in.putExtra("p1", p1.toString().trim());
                in.putExtra("s1", s1.toString().trim());
                startActivity(in);


            }
        });

        show();

    }




    public void show() {
        String server_url = "http://192.168.43.252/supermarkets_API/sql_supermarketProduct .php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Json(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<String,String>();

                params.put("Supermarkets_Name",getSuperName.getStringExtra("s1").toString().trim());
                    return params;
            }
        };
        MySingleTon.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void Json(String response) {

        try {
            JSONArray ja = new JSONArray(response);
            for (int i = 0; i < ja.length(); i++) {
                JSONObject jo = ja.getJSONObject(i);
                String name = jo.getString("Prod_name");
                //String name= jo.getString("Supermarkets_phone");
                arrayList.add(name + "");
            }
            arrayAdapter = new ArrayAdapter(this, android.R.layout.preference_category, android.R.id.title, arrayList);
            listView.setAdapter(arrayAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }









}