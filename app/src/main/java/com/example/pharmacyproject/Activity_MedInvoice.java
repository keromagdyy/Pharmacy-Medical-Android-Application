package com.example.pharmacyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pharmacyproject.data.contractclass.*;
import com.example.pharmacyproject.data.helperclass;

import java.util.ArrayList;
import java.util.Currency;



public class Activity_MedInvoice extends AppCompatActivity {
    AutoCompleteTextView MedName;

    EditText txtQuan,txtPrice,txtTotal;
    Button btnADD,btnDoOrder;
    ListView LstV,LstV2,LstV3;
    helperclass kk = new helperclass(this);
    public static final ArrayList<String> arrayList1 = new ArrayList<>();
    public static final ArrayList<String> arrayList2 = new ArrayList<>();
    public static final ArrayList<String> arrayList3 = new ArrayList<>();
    ArrayAdapter adapter1;
     ArrayAdapter adapter2;
     ArrayAdapter adapter3;
    Intent intent;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__med_invoice);

        final ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = kk.getReadableDatabase();
        final Cursor res = db.rawQuery("select * from " + medicinetable.TABLE_NAME,null);
        res.moveToFirst();

        while(res.isAfterLast() == false)
        {
            String t1 = res.getString(1);
            arrayList.add(t1);
            res.moveToNext();
        }


        txtPrice = findViewById(R.id.txtPrice);
        txtQuan = findViewById(R.id.txtQuan);
        txtTotal = findViewById(R.id.txtTotal);
        btnADD = findViewById(R.id.btnADD);
        btnDoOrder = findViewById(R.id.btnDoOrder);
        LstV = findViewById(R.id.LV);
        LstV2 = findViewById(R.id.LV1);
        LstV3 = findViewById(R.id.LV2);





        MedName =findViewById(R.id.autoCompleteTextView);
        final ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        MedName.setAdapter(adapter);
        final String[] Price = new String[1];
        final double[] TPrice = new double[1];

        MedName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String m = MedName.getText().toString();
                String q = txtQuan.getText().toString();

                if(TextUtils.isEmpty(q))
                {
                    String value = MedName.getText().toString();
                    res.moveToFirst();
                    while(res.isAfterLast() == false)
                    {
                        if(value.equals(res.getString(1)))
                        {
                            Price[0] = res.getString(2);

                            break;
                        }
                        res.moveToNext();
                    }
                    txtPrice.setText(Price[0]);
                }
                else if (TextUtils.isEmpty(m)){
                    Toast.makeText(Activity_MedInvoice.this, "Enter Medicine name", Toast.LENGTH_SHORT).show();
                    txtPrice.setText("");
                    txtTotal.setText("");
                }

                else
                {
                    String value = MedName.getText().toString();
                    res.moveToFirst();
                    while(res.isAfterLast() == false)
                    {
                        if(value.equals(res.getString(1)))
                        {
                            Price[0] = res.getString(2);
                            TPrice[0] = Double.parseDouble(Price[0]) * Double.parseDouble(txtQuan.getText().toString());
                            break;
                        }
                        res.moveToNext();
                    }
                    txtPrice.setText(Price[0]);
                    txtTotal.setText(String.valueOf(TPrice[0]));
                }
            }
        });


        txtQuan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String m = MedName.getText().toString();
                String q = txtQuan.getText().toString();

                if(TextUtils.isEmpty(m) || TextUtils.isEmpty(q))
                {
//                    Toast.makeText(Activity_MedInvoice.this, "Enter Medicine name !!", Toast.LENGTH_SHORT).show();
                    txtTotal.setText("");
                }
                else
                {
                    String value = MedName.getText().toString();
                    res.moveToFirst();
                    while(res.isAfterLast() == false)
                    {
                        if(value.equals(res.getString(1)))
                        {
                            Price[0] = res.getString(2);
                            TPrice[0] = Double.parseDouble(Price[0]) * Double.parseDouble(txtQuan.getText().toString());
                            break;
                        }
                        res.moveToNext();
                    }
                    txtPrice.setText(Price[0]);
                    txtTotal.setText(String.valueOf(TPrice[0]));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = MedName.getText().toString();
                String q = txtQuan.getText().toString();
                String p = txtPrice.getText().toString();
                String pp = txtTotal.getText().toString();

                if(TextUtils.isEmpty(m) || TextUtils.isEmpty(q) || TextUtils.isEmpty(p) || TextUtils.isEmpty(pp)){
                    Toast.makeText(getApplicationContext(),"check for inputs",Toast.LENGTH_SHORT).show();

                }else {
                    arrayList1.add(m);
                    arrayList2.add(q);
                    arrayList3.add(pp);
                    adapter1 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList1);
                    adapter2 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList2);
                    adapter3 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList3);
                    LstV.setAdapter(adapter1);
                    LstV2.setAdapter(adapter2);
                    LstV3.setAdapter(adapter3);
                }

                MedName.setText("");
                txtPrice.setText("");
                txtTotal.setText("");
                txtQuan.setText("");
                Toast.makeText(Activity_MedInvoice.this, "Added successfully", Toast.LENGTH_SHORT).show();


            }
        });



        btnDoOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s1 = arrayList1.size();
                if(s1 == 0) {
                    Toast.makeText(Activity_MedInvoice.this, "Check for Inputs !!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    intent = new Intent(getApplicationContext(), invoice.class);
                    startActivity(intent);

                    MedName.setText("");
                    txtPrice.setText("");
                    txtTotal.setText("");
                    txtQuan.setText("");

                }
            }
        });



        LstV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int which_item = position;

                new AlertDialog.Builder(Activity_MedInvoice.this)
                        .setTitle(" you wont delete"+""+"are you sure?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList1.remove(which_item);
                                adapter1.notifyDataSetChanged();

                                arrayList2.remove(which_item);
                                adapter2.notifyDataSetChanged();

                                arrayList3.remove(which_item);
                                adapter3.notifyDataSetChanged();


                            }
                        })
                        .setNegativeButton("NO",null)
                        .show();
                return true;
            }
        });
        LstV2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int which_item = position;

                new AlertDialog.Builder(Activity_MedInvoice.this)
                        .setTitle(" you wont delete"+""+"are you sure?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList1.remove(which_item);
                                adapter1.notifyDataSetChanged();

                                arrayList2.remove(which_item);
                                adapter2.notifyDataSetChanged();

                                arrayList3.remove(which_item);
                                adapter3.notifyDataSetChanged();


                            }
                        })
                        .setNegativeButton("NO",null)
                        .show();
                return true;
            }
        });
        LstV3.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int which_item = position;

                new AlertDialog.Builder(Activity_MedInvoice.this)
                        .setTitle(" you wont delete"+""+"are you sure?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList1.remove(which_item);
                                adapter1.notifyDataSetChanged();

                                arrayList2.remove(which_item);
                                adapter2.notifyDataSetChanged();

                                arrayList3.remove(which_item);
                                adapter3.notifyDataSetChanged();


                            }
                        })
                        .setNegativeButton("NO",null)
                        .show();
                return true;
            }
        });


    }
}
