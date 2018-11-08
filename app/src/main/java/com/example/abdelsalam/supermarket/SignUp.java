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

public class SignUp extends AppCompatActivity {
    EditText etUsername,etPassword,etFirstName,etLastName,etPhone,etEmail,etAddress;
    String server_url = "http://192.168.43.252/supermarkets_API/InsertClient.php";
    AlertDialog.Builder builder;
    Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etAddress = (EditText) findViewById(R.id.etAddress);
        btnSignUp=(Button)findViewById(R.id.btn_SignUp);
        builder =new AlertDialog.Builder(SignUp.this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Username,Password,FirstName,LastName,Phone,Email,Address;
                Username=etUsername.getText().toString();
                Password=etPassword.getText().toString();
                FirstName=etFirstName.getText().toString();
                LastName=etLastName.getText().toString();
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
                                etFirstName.setText("");
                                etLastName.setText("");
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
                        Toast.makeText(SignUp.this,"Error...",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>params=new HashMap<String,String>();
                        params.put("Clients_firstName",FirstName);
                        params.put("Clients_lastName",LastName);
                        params.put("Clients_phone",Phone);
                        params.put("Clients_email",Email);
                        params.put("Clients_username",Username);
                        params.put("Clients_password",Password);
                        params.put("Clients_Address",Address);
                        return params;
                    }
                };
                MySingleTon.getInstance(SignUp.this).addToRequestQueue(stringRequest);

            }
        });

    }
        }



