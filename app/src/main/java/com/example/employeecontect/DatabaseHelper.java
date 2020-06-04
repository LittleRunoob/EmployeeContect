package com.example.employeecontect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table book ("
            + "id integer primary key autoincrement,"
            + "name varchar(20),"
            + "phone varchar(20),"
            + "position varchar(20),"
            + "sex integer)";


    private Context mContext;

    public DatabaseHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext,"Create Succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
    }

}
