package com.example.abdelsalam.supermarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductContent extends AppCompatActivity {
    TextView TVProductName,TVQty;
    EditText etComment;
    Intent getProductName;
    GridView listView ;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_content);
        TVProductName=(TextView)findViewById(R.id.productName);
        TVQty=(TextView) findViewById(R.id.Qty);
        listView=(GridView)findViewById(R.id.listProductContent);
        etComment=(EditText)findViewById(R.id.productComment);
        getProductName=getIntent();
        TVProductName.setText(getProductName.getStringExtra("p1").toString().trim()+" product");
        showProductContent();




    }



    public void showProductContent() {
        String server_url = "http://192.168.43.252/supermarkets_API/showProductContent.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              //  Json(response);
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
                String name1= jo.getString("Cat_name");
                String name2= jo.getString("Prod_price");

                arrayList.add(name1 +""+name2);
            }
            arrayAdapter = new ArrayAdapter(this, android.R.layout.preference_category, android.R.id.title, arrayList);
            listView.setAdapter(arrayAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }





    int qty=0;

    public void plusQty(View view) {
        qty++;
        TVQty.setText(qty+"");


    }


    public void minQty(View view) {
        if (qty<=0)
        {
            qty=0;
            TVQty.setText(qty+"");

        }
        else
        qty--;
        TVQty.setText(qty+"");

    }
}
