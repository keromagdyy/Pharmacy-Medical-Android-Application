package com.example.pharmacyproject.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class contractclass {
    private contractclass(){}

    public static final class medicinetable implements BaseColumns
    {
        public static final String TABLE_NAME = "medicine";

        public static final String COLUMN_MEDICINE_ID = BaseColumns._ID;
        public static final String COLUMN_MEDICINE_NAME ="name";
        public static final String COLUMN_MEDICINE_PRICE ="price";
        public static final String COLUMN_MEDICINE_QUANTITY ="quantity";
        public static final String COLUMN_MEDICINE_MQUANTITY ="mquantity";
        public static final String COLUMN_MEDICINE_CATEGORY ="category";
        public static final String COLUMN_MEDICINE_SUPPLIERID_FOR ="sid";

    }

    public static final class suppliertable implements BaseColumns
    {
        public static final String TABLE_NAME = "supplier";

        public static final String COLUMN_SUPPLIER_ID = BaseColumns._ID;
        public static final String COLUMN_SUPPLIER_NAME ="name";
        public static final String COLUMN_SUPPLIER_EMAIL ="email";
    }

    public static final class medOfSuppTable implements BaseColumns {
        public static final String TABLE_NAME = "medsupp";

        public static final String COLUMN_MEDSUP_SUPPLIEDATE= "supplierdate";
        public static final String COLUMN_MEDSUP_MEDICINEID_FOR = "mid";
        public static final String COLUMN_MEDSUP_SUPPLIERID_FOR = "sid";
    }

    public static final class invoicetable implements BaseColumns
    {
        public static final String TABLE_NAME = "invoice";

        public static final String COLUMN_INVOICE_ID = BaseColumns._ID;
        public static final String COLUMN_INVOICE_DATE ="date";
        public static final String COLUMN_INVOICE_TIME ="time";
        public static final String COLUMN_INVOICE_TOTAL ="total";
        public static final String COLUMN_INVOICE_EMPLOYEEID_FOR ="employeeid";
    }

    public static final class medOfInvoTable implements BaseColumns
    {
        public static final String TABLE_NAME = "medinvo";

        public static final String COLUMN_MEDINVO_INVOICEID_FOR = BaseColumns._ID;
        public static final String COLUMN_MEDINVO_PRICE ="price";
        public static final String COLUMN_MEDINVO_IQ ="iq";
        public static final String COLUMN_MEDINVO_MEDICINEID_FOR ="medicineid";
    }

    public static final class employeetable implements BaseColumns
    {
        public static final String TABLE_NAME = "employee";

        public static final String COLUMN_EMPLOYEE_ID = BaseColumns._ID;
        public static final String COLUMN_EMPLOYEE_USERNAME ="username";
        public static final String COLUMN_EMPLOYEE_PASSWORD ="password";
        public static final String COLUMN_EMPLOYEE_SHIFT ="shift";
    }

}
