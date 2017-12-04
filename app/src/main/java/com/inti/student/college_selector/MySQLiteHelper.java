package com.inti.student.college_selector;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by WongYeeKhang on 29/11/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_COMMENTS = "comments";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COURSE_NAME = "course_name";
    public static final String COLUMN_COURSE_TYPE = "course_type";
    public static final String COLUMN_REQUIREMENT = "requirement";
    public static final String COLUMN_REQUIREMENT_GRADE = "requirement_grade";
    public static final String COLUMN_COURSE_DURATION= "course_duration";

    private static final String DATABASE_NAME = "colleges.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE  =
            "create table " + TABLE_COMMENTS
            + "("
            +   COLUMN_ID +" "+"integer primary key autoincrement,"
            +   COLUMN_NAME +" "+"text not null,"
            +   COLUMN_COURSE_NAME +" "+"text not null,"
            +   COLUMN_COURSE_TYPE +" "+"text not null,"
            +   COLUMN_REQUIREMENT +" "+"text not null,"
            +   COLUMN_REQUIREMENT_GRADE +" "+"text not null,"
            +   COLUMN_COURSE_DURATION +" "+"text not null"
            + ");";


    public MySQLiteHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to"
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_COMMENTS);
        onCreate(db);
    }
}
