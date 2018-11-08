package com.example.abdelsalam.supermarket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    GridView listView ;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = (GridView)findViewById(R.id.listID);
        arrayList = new ArrayList();


    }




    public void show(View view)
    {
        String server_url = "http://192.168.43.252/sql_show_test.php";
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
                String id = jo.getString("Prod_ID");
                String name= jo.getString("Prod_Name");
               // String address = jo.getString("Stu_Address");
                arrayList.add(id+"\n"+name+"\n");
            }
            arrayAdapter = new ArrayAdapter(this , android.R.layout.activity_list_item , android.R.id.text1,arrayList);
            listView.setAdapter(arrayAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
