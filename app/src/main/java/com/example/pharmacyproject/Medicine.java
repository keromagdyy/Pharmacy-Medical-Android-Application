package com.example.pharmacyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pharmacyproject.data.*;
import com.example.pharmacyproject.data.contractclass.*;

public class Medicine extends AppCompatActivity {
    EditText mednameet,catenameet,priceet,quantityet,mquantityet;
    Button addmedbtn,editmedbtn,deletemedbtn;
    helperclass mhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //helper calss
        mhelper = new helperclass(getApplicationContext());

        //edittexts dec
        setContentView(R.layout.activity_medicine);
        mednameet = findViewById(R.id.mednameet);
        catenameet = findViewById(R.id.catnameet);
        priceet = findViewById(R.id.priceet);
        quantityet = findViewById(R.id.quantityet);
        mquantityet = findViewById(R.id.minquantityet);

        //buttons dec
        addmedbtn = findViewById(R.id.addmedbtn);
        editmedbtn = findViewById(R.id.editmedbtn);
        deletemedbtn = findViewById(R.id.deletemedbtn);

        //method for add new medicine
        addmedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get values from edittext
                String mednamevar = mednameet.getText().toString().trim();
                String catenamevar = catenameet.getText().toString().trim();
                String pricevarstr = priceet.getText().toString().trim();
                String quantityvarstr = quantityet.getText().toString().trim();
                String mquantityvarstr = mquantityet.getText().toString().trim();

                if(TextUtils.isEmpty(mednamevar) || TextUtils.isEmpty(catenamevar) ||
                        TextUtils.isEmpty(pricevarstr) || TextUtils.isEmpty(quantityvarstr) ||
                        TextUtils.isEmpty(mquantityvarstr)){
                    Toast.makeText(getApplicationContext(),"Check For Inputs Data !!!!!",Toast.LENGTH_SHORT).show();
                }else {

                    double pricevar = Integer.parseInt(priceet.getText().toString());
                    double quantityvar = Integer.parseInt(quantityet.getText().toString());
                    double mquantityvar = Integer.parseInt(mquantityet.getText().toString());

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(medicinetable.COLUMN_MEDICINE_NAME,mednamevar);
                    contentValues.put(medicinetable.COLUMN_MEDICINE_PRICE,pricevar);
                    contentValues.put(medicinetable.COLUMN_MEDICINE_QUANTITY,quantityvar);
                    contentValues.put(medicinetable.COLUMN_MEDICINE_MQUANTITY,mquantityvar);
                    contentValues.put(medicinetable.COLUMN_MEDICINE_CATEGORY,catenamevar);

                    SQLiteDatabase db = mhelper.getWritableDatabase();
                    db.insert(medicinetable.TABLE_NAME,null,contentValues);
                    Toast.makeText(getApplicationContext(),"Added successfully",Toast.LENGTH_SHORT).show();
                }
            }


        });


        //method for etid medicine
        editmedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get values from edittext
                String mednamevar = mednameet.getText().toString().trim();
                String catenamevar = catenameet.getText().toString().trim();
                String pricevarstr = priceet.getText().toString().trim();
                String quantityvarstr = quantityet.getText().toString().trim();
                String mquantityvarstr = mquantityet.getText().toString().trim();

                if(TextUtils.isEmpty(mednamevar) || TextUtils.isEmpty(catenamevar) ||
                        TextUtils.isEmpty(pricevarstr) || TextUtils.isEmpty(quantityvarstr) ||
                        TextUtils.isEmpty(mquantityvarstr)){
                    Toast.makeText(getApplicationContext(),"Check For Inputs Data !!!!!",Toast.LENGTH_SHORT).show();
                }else {

                    double pricevar = Integer.parseInt(priceet.getText().toString());
                    double quantityvar = Integer.parseInt(quantityet.getText().toString());
                    double mquantityvar = Integer.parseInt(mquantityet.getText().toString());

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(medicinetable.COLUMN_MEDICINE_NAME,mednamevar);
                    contentValues.put(medicinetable.COLUMN_MEDICINE_PRICE,pricevar);
                    contentValues.put(medicinetable.COLUMN_MEDICINE_QUANTITY,quantityvar);
                    contentValues.put(medicinetable.COLUMN_MEDICINE_MQUANTITY,mquantityvar);
                    contentValues.put(medicinetable.COLUMN_MEDICINE_CATEGORY,catenamevar);

                    SQLiteDatabase db = mhelper.getWritableDatabase();
                    db.update(medicinetable.TABLE_NAME,contentValues,"name=?",new String[]{mednamevar});
                    Toast.makeText(getApplicationContext(),"Edit successfully",Toast.LENGTH_SHORT).show();
                }
            }


        });

        deletemedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get values from edittext
                String mednamevar = mednameet.getText().toString().trim();
                if(TextUtils.isEmpty(mednamevar)){
                    Toast.makeText(getApplicationContext(),"Check For Inputs Data !!!!!",Toast.LENGTH_SHORT).show();
                }else {
                    SQLiteDatabase db = mhelper.getWritableDatabase();
                    db.delete(medicinetable.TABLE_NAME,"name=?",new String[]{mednamevar});
                    Toast.makeText(getApplicationContext(),"Deleted successfully",Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}