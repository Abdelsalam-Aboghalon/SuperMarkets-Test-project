package com.example.abdelsalam.supermarket;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class signUpSuperMarket extends AppCompatActivity {
    EditText etUsername,etPassword,etName,etPhone,etEmail,etAddress;
    String server_url = "http://192.168.43.252/supermarkets_API/InsertSupermarket.php";
    AlertDialog.Builder builder;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_super_market);
        etUsername = (EditText) findViewById(R.id.et_Username);
        etPassword = (EditText) findViewById(R.id.et_Password);
        etName = (EditText) findViewById(R.id.et_Name);
        etPhone = (EditText) findViewById(R.id.et_Phone);
        etEmail = (EditText) findViewById(R.id.et_Email);
        etAddress = (EditText) findViewById(R.id.et_Address);
        btnSignUp=(Button)findViewById(R.id.btn_SignUp);
        builder =new AlertDialog.Builder(signUpSuperMarket.this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Username,Password,Name,Phone,Email,Address;
                Username=etUsername.getText().toString();
                Password=etPassword.getText().toString();
                Name=etName.getText().toString();
                Phone=etPhone.getText().toString();
                Email=etEmail.getText().toString();
                Address=etAddress.getText().toString();
                StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        builder.setTitle("Server Response");
                        builder.setMessage("response :"+response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                etUsername.setText("");
                                etPassword.setText("");
                                etName.setText("");
                                etPhone.setText("");
                                etEmail.setText("");
                                etAddress.setText("");
                            }
                        });
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(signUpSuperMarket.this,"Error...",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>params=new HashMap<String,String>();
                        params.put("Supermarket_Name",Name);
                        params.put("Supermarket_phone",Phone);
                        params.put("Supermarket_email",Email);
                        params.put("Supermarket_username",Username);
                        params.put("Supermarket_password",Password);
                        params.put("Supermarket_Address",Address);
                        return params;
                    }
                };
                MySingleTon.getInstance(signUpSuperMarket.this).addToRequestQueue(stringRequest);

            }
        });

    }
}



