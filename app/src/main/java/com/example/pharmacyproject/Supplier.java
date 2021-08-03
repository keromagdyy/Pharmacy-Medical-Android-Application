package com.example.pharmacyproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pharmacyproject.data.*;
import com.example.pharmacyproject.data.contractclass.*;

import java.security.KeyStore;
import java.util.ArrayList;

public class Supplier extends AppCompatActivity {
    EditText txtName,txtMail;
    Button btnAdd;
    ListView LstV,LstV2;

    ArrayList<String> arrayList = new ArrayList();

    ArrayAdapter adapter;

    helperclass H = new helperclass(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);
        txtName = findViewById(R.id.txtName);
        txtMail = findViewById(R.id.txtMail);
        btnAdd = findViewById(R.id.btnAdd4);
        LstV = findViewById(R.id.ListV);
//        LstV2 = findViewById(R.id.ListV2);

        showData();



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase dbW = H.getWritableDatabase();
                ContentValues cv = new ContentValues();

                String name = txtName.getText().toString();
                String Mail = txtMail.getText().toString();
                cv.put(suppliertable.COLUMN_SUPPLIER_NAME,name);
                cv.put(suppliertable.COLUMN_SUPPLIER_EMAIL,Mail);
                dbW.insert(suppliertable.TABLE_NAME,null,cv);


                showData();
                Toast.makeText(Supplier.this, "Added !", Toast.LENGTH_SHORT).show();

            }
        });

        LstV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final int which_item = position;

                new AlertDialog.Builder(Supplier.this)
                        .setTitle(" you wont delete"+""+"are you sure?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String s = arrayList.get(position);
                                arrayList.remove(which_item);
                                adapter.notifyDataSetChanged();
//                                int i = s.charAt(10);

                                SQLiteDatabase db3 = H.getWritableDatabase();
                                db3.delete(suppliertable.TABLE_NAME,suppliertable.COLUMN_SUPPLIER_ID+" = ?",new String[]{s.charAt(10)+""});
                                Toast.makeText(Supplier.this, "Deleted", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("NO",null)
                        .show();
                return true;
            }
        });


    }

    public void showData()
    {
        arrayList.clear();
        adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);

        LstV.setAdapter(adapter);
        SQLiteDatabase dbR = H.getReadableDatabase();
        Cursor res = dbR.rawQuery("select * from " + suppliertable.TABLE_NAME,null);
        res.moveToFirst();
        while(res.isAfterLast() == false)
        {
            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);


            arrayList.add("ID : \t\t\t\t\t"+t1+"\nName :\t\t"+t2+" \nEmail : \t\t"+t3);
            adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
            LstV.setAdapter(adapter);


            res.moveToNext();
        }
    }

}
