package com.enggemy22.cap.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseClass extends SQLiteOpenHelper {

    public static final String DatabaseName="database";
    public static final String TABLE_NAME="univeristy";
    public static final String Col_1 ="University_name";
    private String Col_2="Average_GPA";
    private String Col_3="Sat_score_range";
    private String Col_4="ACT_score_range";
    private String Col_5="Finanitial_aid_avilability";
    private String Col_6="Acceptance_rate";
    private String Col_7="Average tuition fees (Out-of-state)";
    private String Col_8="Country";
    private String Col_9="Global_QS_ranking ";

    public DatabaseClass(Context context) {
        super(context, DatabaseName, null, 1);
        SQLiteDatabase database= this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL("create table " +TABLE_NAME+" (University_name TEXT  ,Average_GPA INTEGER PRIMARY KEY ,Sat_score_range TEXT,ACT_score_range TEXT,Finanitial_aid_avilability TEXT,Acceptance_rate  TEXT,Average_tuition_fees TEXT,Country TEXT,Global_QS_ranking TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insertIntoDatabase(String Average_GPA ){
        SQLiteDatabase database= this.getWritableDatabase();
        ContentValues values= new ContentValues();

          return true;
    }
}
