package com.example.abdelsalam.supermarket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class AccountType extends AppCompatActivity {
    String AccountType;
    private Spinner spinner;
    Button btnChoose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);
        btnChoose=(Button) findViewById(R.id. btnChoose);
        spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AccountType = parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    btnChoose.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (AccountType){
                case "Client":
                    openActivitiesClient();
                    break;
                case "Supermarket":
                    openActivitiesMarket();
                    break;
                case "Delivery":
                    openActivitiesDelivery();
                    break;
                    default:break;
            }
        }
    });


    }










    public void openActivitiesClient() {
        startActivity(new Intent(this,LoginClient.class));
    }

    public void openActivitiesMarket() {
        startActivity(new Intent(this,LoginSuperMarket.class));
    }

    public void openActivitiesDelivery() {
        startActivity(new Intent(this,DeliveryLogin.class));
    }
}
