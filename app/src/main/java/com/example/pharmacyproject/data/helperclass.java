package com.example.pharmacyproject.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.pharmacyproject.data.contractclass.*;

public class helperclass extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "phDB.dp";
    private static final int DATABASE_VERSION = 1;
    public helperclass(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREAT_SUP_TABLE = "CREATE TABLE " + suppliertable.TABLE_NAME + " (" +
                suppliertable.COLUMN_SUPPLIER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                suppliertable.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL, "+
                suppliertable.COLUMN_SUPPLIER_EMAIL + " TEXT UNIQUE);";

        String SQL_CREAT_MED_TABLE = "CREATE TABLE " +medicinetable.TABLE_NAME + " (" +
                medicinetable.COLUMN_MEDICINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                medicinetable.COLUMN_MEDICINE_NAME + " TEXT NOT NULL, "+
                medicinetable.COLUMN_MEDICINE_PRICE + " DECIMAL(10,3) NOT NULL, "+
                medicinetable.COLUMN_MEDICINE_QUANTITY + " INTEGER NOT NULL, "+
                medicinetable.COLUMN_MEDICINE_MQUANTITY + " INTEGER NOT NULL DEFAULT 0, "+
                medicinetable.COLUMN_MEDICINE_CATEGORY + " TEXT NOT NULL," +
                medicinetable.COLUMN_MEDICINE_SUPPLIERID_FOR + " INTEGER," +
                "FOREIGN KEY ("+medicinetable.COLUMN_MEDICINE_SUPPLIERID_FOR +") REFERENCES "+suppliertable.TABLE_NAME+"("+suppliertable.COLUMN_SUPPLIER_ID+")  );";


        String SQL_CREAT_SUPMED_TABLE = "CREATE TABLE " + medOfSuppTable.TABLE_NAME + " (" +
                medOfSuppTable.COLUMN_MEDSUP_SUPPLIEDATE + " DATE UNIQUE, " +
                medOfSuppTable.COLUMN_MEDSUP_MEDICINEID_FOR + " INTEGER, "+
                medOfSuppTable.COLUMN_MEDSUP_SUPPLIERID_FOR+ " INTEGER," +
                "FOREIGN KEY ("+medOfSuppTable.COLUMN_MEDSUP_MEDICINEID_FOR+") REFERENCES "+medicinetable.TABLE_NAME+"("+medicinetable.COLUMN_MEDICINE_ID+")," +
                "FOREIGN KEY ("+medOfSuppTable.COLUMN_MEDSUP_SUPPLIERID_FOR+") REFERENCES "+suppliertable.TABLE_NAME+"("+suppliertable.COLUMN_SUPPLIER_ID+")  );";


        String SQL_CREAT_EMPLOYEE_TABLE = "CREATE TABLE " +employeetable.TABLE_NAME + " (" +
                employeetable.COLUMN_EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                employeetable.COLUMN_EMPLOYEE_USERNAME + " TEXT UNIQUE NOT NULL, "+
                employeetable.COLUMN_EMPLOYEE_PASSWORD+ " TEXT NOT NULL," +
                employeetable.COLUMN_EMPLOYEE_SHIFT+ " TEXT);";


        String SQL_CREAT_INVOICE_TABLE = "CREATE TABLE " +invoicetable.TABLE_NAME + " (" +
                invoicetable.COLUMN_INVOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                invoicetable.COLUMN_INVOICE_DATE + " DATE, "+
                invoicetable.COLUMN_INVOICE_TIME+ " DATETIME," +
                invoicetable.COLUMN_INVOICE_TOTAL+ " DECIMAL(10.3)," +
                invoicetable.COLUMN_INVOICE_EMPLOYEEID_FOR+ " INTEGER," +
                "FOREIGN KEY ("+invoicetable.COLUMN_INVOICE_EMPLOYEEID_FOR+") REFERENCES "+employeetable.TABLE_NAME+"("+employeetable.COLUMN_EMPLOYEE_ID+") );";


        String SQL_CREAT_MEDINVO_TABLE = "CREATE TABLE " +medOfInvoTable.TABLE_NAME + " (" +
                medOfInvoTable.COLUMN_MEDINVO_MEDICINEID_FOR + " INTEGER, " +
                medOfInvoTable.COLUMN_MEDINVO_INVOICEID_FOR+ " INTEGER, "+
                medOfInvoTable.COLUMN_MEDINVO_PRICE+ " DECIMAL(10,3)," +
                medOfInvoTable.COLUMN_MEDINVO_IQ+ " INTEGER," +
                "FOREIGN KEY ("+medOfInvoTable.COLUMN_MEDINVO_MEDICINEID_FOR+") REFERENCES "+medicinetable.TABLE_NAME+"("+medicinetable.COLUMN_MEDICINE_ID+")," +
                "FOREIGN KEY ("+medOfInvoTable.COLUMN_MEDINVO_INVOICEID_FOR+") REFERENCES "+invoicetable.TABLE_NAME+"("+invoicetable.COLUMN_INVOICE_ID+")  );";


        db.execSQL(SQL_CREAT_SUP_TABLE);
        db.execSQL(SQL_CREAT_MED_TABLE);
        db.execSQL(SQL_CREAT_SUPMED_TABLE);
        db.execSQL(SQL_CREAT_EMPLOYEE_TABLE);
        db.execSQL(SQL_CREAT_INVOICE_TABLE);
        db.execSQL(SQL_CREAT_MEDINVO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}





