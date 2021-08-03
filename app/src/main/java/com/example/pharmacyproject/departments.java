package com.example.pharmacyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class departments extends AppCompatActivity {
Button Invoice,supplier,Medicines,Request,aboutus,exit;
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departments);
        Invoice = findViewById(R.id.Invoicebtn);
        Request = findViewById(R.id.Requestbtn);
        Medicines = findViewById(R.id.Medicinesbtn);
        supplier = findViewById(R.id.Supplierbtn);
        aboutus = findViewById(R.id.aboutbtn);
        exit = findViewById(R.id.Exitbtn);
        Invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),Activity_MedInvoice.class);
                startActivity(intent);
            }
        });
        supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),Supplier.class);
                startActivity(intent);
            }
        });
        Medicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),Medicine.class);
                startActivity(intent);
            }
        });
        Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),company.class);
                startActivity(intent);
            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(),aboutus.class);
                startActivity(intent);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }
}
