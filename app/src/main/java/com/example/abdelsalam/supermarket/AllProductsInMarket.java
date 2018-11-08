package com.example.abdelsalam.supermarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class AllProductsInMarket extends AppCompatActivity {

    GridView listView ;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter ;
    EditText etProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products_in_market);
        listView = (GridView)findViewById(R.id.listOfAllProduct);
        arrayList = new ArrayList();
        etProd=(EditText)findViewById(R.id.etProductName);
 show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s=(String)listView.getItemAtPosition(position);
                Intent in=new Intent(AllProductsInMarket.this,ProductContent.class);
                in.putExtra("P1",s.toString().trim());
                startActivity(in);


            }
        });
    }




    public void show()
    {
        String server_url = "http://192.168.43.252/supermarkets_API/sql_All_Product.php";
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
            for (int i = 0 ; i < ja.length() ; i++)
            {
                JSONObject jo = ja.getJSONObject(i);
                String id = jo.getString("Prod_name");
                String name= jo.getString("Cat_name");
                arrayList.add(id+"\n"+name+"\n");
            }
            arrayAdapter = new ArrayAdapter(this , android.R.layout.preference_category , android.R.id.title,arrayList);
            listView.setAdapter(arrayAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void searchProd(View view) {
        


    }
}