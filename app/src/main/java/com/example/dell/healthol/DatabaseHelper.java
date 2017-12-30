package com.example.dell.healthol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Priyansh on 21-12-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "healthol.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_DOB = "dob";

    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null  ,"+
            "name text not null , email text not null , password text not null , phone integer not null , address text not null ,"+
            "dob text not null);";

    public DatabaseHelper(Context context){
        super(context , DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    public void insertContact(Contact c)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        String query="select * from contacts";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();
        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_EMAIL,c.getEmail());
        values.put(COLUMN_PASSWORD,c.getPassword());
        values.put(COLUMN_PHONE,c.getPhone());
        values.put(COLUMN_ADDRESS,c.getAddress());
        values.put(COLUMN_DOB,c.getDob());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public String searchPass(String email)
    {
        db=this.getReadableDatabase();
        String query ="select email,password from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        String u,p;
        p="NOT FOUND";

        if (cursor.moveToFirst())
        {
            do {
                u=cursor.getString(0);

                if (u.equals(email)){
                    p=cursor.getString(1);
                    break;
                }
            }while (cursor.moveToNext());
        }
        return p;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

       String query="DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
