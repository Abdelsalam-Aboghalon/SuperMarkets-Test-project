package com.example.abdelsalam.supermarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeliveryLogin extends AppCompatActivity {
    EditText etUserName,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_login);
        etUserName=(EditText)findViewById(R.id.input_WarkerName);
        etPassword=(EditText)findViewById(R.id.input_WorkerPassword);

    }
    public void openSignUp(View view){

        startActivity(new Intent(this,SignUp.class));


    }

    public void logIn(View view)
    {
        final String Username,Password;
        Username=etUserName.getText().toString();
        Password=etPassword.getText().toString();
        String server_url = "http://192.168.43.252/supermarkets_API/LoginDelivery.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Login_check(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<String,String>();
                params.put("worker_username",Username);
                params.put("worker_password",Password);

                return params;
            }
        };
        MySingleTon.getInstance(this).addToRequestQueue(stringRequest);
    }


    public void Login_check(String response)
    {
        if(response.toString().trim().equals("success"))
            openClientPage();
        else
            Toast.makeText(this,response+"",Toast.LENGTH_LONG).show();


    }
    public void openClientPage(){
        Intent in =new Intent(this,Main2Activity.class);
        startActivity(in);



    }
}
