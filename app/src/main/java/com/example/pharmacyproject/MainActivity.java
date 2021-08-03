package com.example.pharmacyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pharmacyproject.data.contractclass.*;
import com.example.pharmacyproject.data.*;

public class MainActivity extends AppCompatActivity {
Button btnLogin,btnSignUp;
EditText txtname,txtpassword;

    helperclass H = new helperclass(this);

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        txtname = findViewById(R.id.txtUname);
        txtpassword = findViewById(R.id.txtPass);

        final SQLiteDatabase db = H.getReadableDatabase();
        final boolean[] ch = {false};

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = txtname.getText().toString();
                String pass = txtpassword.getText().toString();

                Cursor res = db.rawQuery("select * from " + employeetable.TABLE_NAME,null);
                res.moveToFirst();
                while (res.isAfterLast() == false)
                {
                    String DBname = res.getString(1);
                    String DBpass = res.getString(2);
                    if(name.equals(DBname) && pass.equals(DBpass))
                    {
                        Intent intent = new Intent(getApplicationContext(),departments.class);
                        startActivity(intent);
                        ch[0] = true;
                        break;
                    }
                    res.moveToNext();
                }

                if (ch[0] == true)
                {
                    Toast.makeText(MainActivity.this, "Welcome " + name, Toast.LENGTH_SHORT).show();
                    ch[0] = false;
                    txtname.setText("");
                    txtpassword.setText("");
                }else
                {
                    Toast.makeText(MainActivity.this, " CHECK FOR INPUTS\n You have an error in mail or password ! ", Toast.LENGTH_SHORT).show();
                    txtpassword.setText("");
                }

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),Sign_Up.class);
                startActivity(intent);
            }
        });
    }
}
