package com.example.abdelsalam.supermarket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;

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

public class AddCategories extends AppCompatActivity {
    EditText etCat;
    GridView listViewCategories;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_categories);
        etCat= (EditText) findViewById(R.id.CatName);
        listViewCategories = (GridView) findViewById(R.id.listOfAllSupermarket);
        arrayList = new ArrayList();
        show();
    }



    public void InsertMyCat(View view){
        String server_url = "http://192.168.43.252/supermarkets_API/AddCategories.php";

        StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<String,String>();
                params.put("Cat_name",etCat.getText().toString().trim());
                return params;
            }
        };
        MySingleTon.getInstance(this).addToRequestQueue(stringRequest);




        show();
    }








    public void show() {
        String server_url = "http://192.168.43.252/supermarkets_API/Show_AllCategories.php";
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
                String name = jo.getString("Cat_name");
                arrayList.add(name);
            }
            arrayAdapter = new ArrayAdapter(this, android.R.layout.preference_category, android.R.id.title, arrayList);
            listViewCategories.setAdapter(arrayAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }








}
