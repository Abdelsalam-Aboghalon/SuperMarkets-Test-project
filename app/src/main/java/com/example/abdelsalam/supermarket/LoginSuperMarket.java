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

public class LoginSuperMarket extends AppCompatActivity {
    EditText etMarketName,etMarketPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_super_market);
        etMarketName=(EditText)findViewById(R.id.input_MarketName);
        etMarketPassword=(EditText)findViewById(R.id.input_marketPassword);
    }

    public void openSignUpMarket(View view) {
        startActivity(new Intent(this,signUpSuperMarket.class));
    }

    public void logInMarket(View view) {

        final String Username,Password;
        Username=etMarketName.getText().toString();
        Password=etMarketPassword.getText().toString();
        String server_url = "http://192.168.43.252/supermarkets_API/LoginSupermarket.php";
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
                params.put("Supermarkets_username",Username);
                params.put("Supermarkets_password",Password);

                return params;
            }
        };
        MySingleTon.getInstance(this).addToRequestQueue(stringRequest);
    }


    public void Login_check(String response)
    {
        if(response.toString().trim().equals("success"))
            openMarketPage();
        else
            Toast.makeText(this,response+"",Toast.LENGTH_LONG).show();


    }
    public void openMarketPage(){
        Intent in =new Intent(this,Main2Activity.class);
        startActivity(in);



    }
}
