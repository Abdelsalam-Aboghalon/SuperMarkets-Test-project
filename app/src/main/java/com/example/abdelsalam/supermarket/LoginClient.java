package com.example.abdelsalam.supermarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class LoginClient extends AppCompatActivity {
    EditText etUserName,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginclient);
        etUserName=(EditText)findViewById(R.id.input_username);
        etPassword=(EditText)findViewById(R.id.input_password);

    }
public void openSignUp(View view){

    startActivity(new Intent(this,SignUp.class));


}

    public void logIn(View view)
    {
        final String Username,Password;
        Username=etUserName.getText().toString();
        Password=etPassword.getText().toString();
        String server_url = "http://192.168.43.252/supermarkets_API/LoginClients.php";
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
                params.put("Clients_username",Username);
                params.put("Clients_password",Password);

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
    Intent in =new Intent(this,AllSuperMarkets.class);
    startActivity(in);



    }
}
