package com.sqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SQLite";
    public static final String COMPANIES_TABLE_NAME = "Companies";
    public static final String COMPANIES_COLUMN_ID = "ID";
    public static final String COMPANIES_COLUMN_NAME = "Name";
    public static final String COMPANIES_COLUMN_EMAIL = "Email";
    public static final String COMPANIES_COLUMN_URL = "Url";
    public static final String COMPANIES_COLUMN_PRODUCTSSERVICES = "ProductsServices";
    public static final String COMPANIES_COLUMN_PHONE = "Phone";
    public static final String COMPANIES_COLUMN_CLASSIFICATION = "Classification";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table " + COMPANIES_TABLE_NAME + "("+ COMPANIES_COLUMN_ID
                +" integer primary key, "+ COMPANIES_COLUMN_NAME +" text, "+ COMPANIES_COLUMN_URL +" TEXT, "
                + COMPANIES_COLUMN_PHONE +" TEXT, " + COMPANIES_COLUMN_EMAIL + " TEXT, "
                + COMPANIES_COLUMN_PRODUCTSSERVICES + " TEXT, "+ COMPANIES_COLUMN_CLASSIFICATION +" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + COMPANIES_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertCompany(Company company){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newCompany = new ContentValues();

        newCompany.put(COMPANIES_COLUMN_ID, company.getId());
        newCompany.put(COMPANIES_COLUMN_NAME, company.getName());
        newCompany.put(COMPANIES_COLUMN_URL, company.getUrl());
        newCompany.put(COMPANIES_COLUMN_PHONE, company.getPhone());
        newCompany.put(COMPANIES_COLUMN_EMAIL, company.getEmail());
        newCompany.put(COMPANIES_COLUMN_PRODUCTSSERVICES, company.getProductsServices());
        newCompany.put(COMPANIES_COLUMN_CLASSIFICATION, company.getClassification());

        db.insert(COMPANIES_TABLE_NAME, null, newCompany);

        return true;
    }

    public ArrayList<Company> listAllCompanies(){
        ArrayList<Company> companies = new ArrayList<Company>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + COMPANIES_TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false){
            companies.add(new Company(res.getInt(res.getColumnIndex(COMPANIES_COLUMN_ID)),
                    res.getString(res.getColumnIndex(COMPANIES_COLUMN_NAME)),
                    res.getString(res.getColumnIndex(COMPANIES_COLUMN_URL)),
                    res.getString(res.getColumnIndex(COMPANIES_COLUMN_PHONE)),
                    res.getString(res.getColumnIndex(COMPANIES_COLUMN_EMAIL)),
                    res.getString(res.getColumnIndex(COMPANIES_COLUMN_PRODUCTSSERVICES)),
                    res.getString(res.getColumnIndex(COMPANIES_COLUMN_CLASSIFICATION))
            ));
            res.moveToNext();
        }

        db.close();

        return companies;
    }

    public Integer deleteCompany (Integer id){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(COMPANIES_TABLE_NAME, "id = ?", new String[] {Integer.toString(id)});
    }

    public boolean updateCompany(Company company){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues newCompany = new ContentValues();

        newCompany.put(COMPANIES_COLUMN_NAME, company.getName());
        newCompany.put(COMPANIES_COLUMN_URL, company.getUrl());
        newCompany.put(COMPANIES_COLUMN_PHONE, company.getPhone());
        newCompany.put(COMPANIES_COLUMN_EMAIL, company.getEmail());
        newCompany.put(COMPANIES_COLUMN_PRODUCTSSERVICES, company.getProductsServices());
        newCompany.put(COMPANIES_COLUMN_CLASSIFICATION, company.getClassification());

        db.update(COMPANIES_TABLE_NAME, newCompany, "id = ?", new String[] {Integer.toString(company.getId())});

        return true;
    }

}
