package com.example.pharmacyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pharmacyproject.data.*;
import com.example.pharmacyproject.data.contractclass.*;


public class Sign_Up extends AppCompatActivity {
    helperclass H = new helperclass(this);

    EditText txtUserName,txtPassword,txtCPassword;
    Button btnAdd;
    Spinner txtShift;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        txtUserName = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        txtShift = findViewById(R.id.txtShift);
        btnAdd = findViewById(R.id.btnAdd5);
        txtCPassword = findViewById(R.id.txtCPassword);

        String[] arr = new String[]{
          "Morning","Afternoon","Night","Midnight"
        };

        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        txtShift.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();
                String shift = txtShift.getSelectedItem().toString();
                String Cpassword = txtCPassword.getText().toString();



                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"check for inputs",Toast.LENGTH_SHORT).show();
                }
                else{
                    if (password.equals(Cpassword)) {
                        SQLiteDatabase db = H.getWritableDatabase();
                        ContentValues cv = new ContentValues();

                        cv.put(employeetable.COLUMN_EMPLOYEE_USERNAME, name);
                        cv.put(employeetable.COLUMN_EMPLOYEE_PASSWORD, password);
                        cv.put(employeetable.COLUMN_EMPLOYEE_SHIFT, shift);
                        db.insert(employeetable.TABLE_NAME, null, cv);

                        Toast.makeText(Sign_Up.this, "Added Successfully !", Toast.LENGTH_SHORT).show();

                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"check for your password",Toast.LENGTH_SHORT).show();
                        txtPassword.setText("");
                        txtCPassword.setText("");
                    }
                }


            }
        });

    }
}
