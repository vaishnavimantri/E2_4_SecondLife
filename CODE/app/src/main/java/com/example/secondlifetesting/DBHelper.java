package com.example.secondlifetesting;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import com.example.secondlifetesting.LoginActivity;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    private Context context;
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Userdetails";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";



    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DB_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                 + COLUMN_PHONE + " TEXT,"
                + COLUMN_EMAIL + " TEXT UNIQUE,"
                + COLUMN_PASSWORD + " TEXT);";
        MyDB.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(MyDB);
    }

    void insertData(String email, String password, String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PHONE, number);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD, password);
        long result = db.insert(TABLE_NAME, null, cv);


        if(result == -1){
            Toast.makeText(context, "user already exists", Toast.LENGTH_SHORT).show();

        }

        else
            Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show();
    }


    public Boolean checkemail(String email)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.query("Userdetails", null, " email = ? ", new String[] {email}, null, null ,null);
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkemailpassword(String email, String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.query("Userdetails", null, " email = ? and password = ? ", new String[] {email,password}, null, null ,null);
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checknumber(String number)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.query("Userdetails", null, "number = ?", new String[] {number}, null, null ,null);
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }



}
