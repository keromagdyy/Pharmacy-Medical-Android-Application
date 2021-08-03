package com.example.pharmacyproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pharmacyproject.Activity_MedInvoice.*;
import com.example.pharmacyproject.data.contractclass.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.example.pharmacyproject.data.*;




public class invoice extends AppCompatActivity {
    ListView ListV,ListV2,ListV3;
    Button btnPrint;
    ArrayAdapter adapter1,adapter2,adapter3;
    TextView txtI_ID,txtDate,txtTime,txtTPrice;
    Intent intent;


    helperclass kk = new helperclass(this);


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        ListV = findViewById(R.id.ListV);
        ListV2 = findViewById(R.id.ListV1);
        ListV3 = findViewById(R.id.ListV2);
        txtI_ID = findViewById(R.id.txtI_ID);
        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);
        txtTPrice = findViewById(R.id.txtTPrice);
        btnPrint = findViewById(R.id.btnprint);


        ArrayList<String> AL = new ArrayList<>();
        ArrayList<String> arrayList1 = Activity_MedInvoice.arrayList1;
        ArrayList<String> arrayList2 = Activity_MedInvoice.arrayList2;
        ArrayList<String> arrayList3 = Activity_MedInvoice.arrayList3;

        adapter1 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList1);
        adapter2 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList2);
        adapter3 = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList3);

        ListV.setAdapter(adapter1);
        ListV2.setAdapter(adapter2);
        ListV3.setAdapter(adapter3);

        Double count = 0.0;


        for (int i = 0; i < arrayList3.size() ; i++) {
            count += Double.parseDouble(arrayList3.get(i));
        }

        txtTPrice.setText(count.toString());

        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        txtDate.setText(formatDate.format(date));
        txtTime.setText(formatTime.format(date));




        SQLiteDatabase db1 = kk.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(invoicetable.COLUMN_INVOICE_DATE,txtDate.getText().toString());
        cv.put(invoicetable.COLUMN_INVOICE_TIME,txtTime.getText().toString());
        cv.put(invoicetable.COLUMN_INVOICE_TOTAL,txtTPrice.getText().toString());
        db1.insert(invoicetable.TABLE_NAME,null,cv);

        db1 = kk.getReadableDatabase();


        Cursor res = db1.rawQuery("select * from " + invoicetable.TABLE_NAME,null);
        res.moveToLast();
        String id = res.getString(0);

        txtI_ID.setText(id);


        Cursor res2 = db1.rawQuery("select * from " + medicinetable.TABLE_NAME,null);
        for (int i = 0; i < arrayList1.size() ; i++) {
            res2.moveToFirst();
            String s = arrayList1.get(i);
            while (res2.isAfterLast() == false) {
                String t1 = res2.getString(1);
                String t2 = res2.getString(0);
                String t3 = res2.getString(3);
                if (s.equals(t1)) {

                    AL.add(t2);
                    int q = Integer.parseInt(t3) - Integer.parseInt(arrayList2.get(i));
                    ContentValues cv2 = new ContentValues();
                    cv2.put(medicinetable.COLUMN_MEDICINE_QUANTITY,q);
                    db1.update(medicinetable.TABLE_NAME,cv2,medicinetable.COLUMN_MEDICINE_ID+"=?",new String[]{t2});
                    break;
                }
                res2.moveToNext();
            }
        }

        for (int i = 0; i < arrayList1.size(); i++) {
            ContentValues cv3 = new ContentValues();
            cv3.put(medOfInvoTable.COLUMN_MEDINVO_INVOICEID_FOR,txtI_ID.getText().toString());

            int sMID = Integer.parseInt(AL.get(i));
            cv3.put(medOfInvoTable.COLUMN_MEDINVO_MEDICINEID_FOR,sMID);

            int sIQ = Integer.parseInt(arrayList2.get(i));
            cv3.put(medOfInvoTable.COLUMN_MEDINVO_IQ,sIQ);

            String sPRI = arrayList3.get(i);
            cv3.put(medOfInvoTable.COLUMN_MEDINVO_PRICE,sPRI);

            db1.insert(medOfInvoTable.TABLE_NAME,null,cv3);

        }


        txtTime.setText(formatTime.format(date));

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity_MedInvoice.arrayList1.clear();
                Activity_MedInvoice.arrayList2.clear();
                Activity_MedInvoice.arrayList3.clear();

                intent = new Intent(getApplicationContext(), Activity_MedInvoice.class);
                startActivity(intent);
            }
        });
    }
}
