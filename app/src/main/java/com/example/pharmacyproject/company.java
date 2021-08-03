package com.example.pharmacyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.pharmacyproject.data.*;
import com.example.pharmacyproject.data.contractclass.*;

public class company extends AppCompatActivity {
EditText doctorname,companyname,medicinename,quantity;
Button doorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        doctorname = findViewById(R.id.doname);
        companyname = findViewById(R.id.comname);
        medicinename = findViewById(R.id.medname);
        quantity = findViewById(R.id.quantityid);
        doorder = findViewById(R.id.btndoorder);
        final helperclass mhelper = new helperclass(this);
        doorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cmname = companyname.getText().toString();
                String email = companyname.getText().toString();
                //SQLiteDatabase db =mhelper.getReadableDatabase();
                //Cursor cursor = db.query(suppliertable.TABLE_NAME,pro,"named=?",cmname,null,null,null);
                //String email = cursor.getString(cursor.getColumnIndex())
                String content =" hello from doctor : " + doctorname.getText().toString()
                        +"\n we need medicine name : " + medicinename.getText().toString()
                        +"\n Quantity : " + quantity.getText().toString()
                        +"\n Thank You!";
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + email)); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_SUBJECT, "Medicine Order");
                intent.putExtra(Intent.EXTRA_TEXT, content);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                doctorname.setText("");
                companyname.setText("");
                medicinename.setText("");
                quantity.setText("");
            }
        });
    }
}
