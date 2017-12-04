package com.inti.student.college_selector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.inti.student.college_selector.MySQLiteHelper.COLUMN_COURSE_TYPE;
import static com.inti.student.college_selector.MySQLiteHelper.COLUMN_NAME;
import static com.inti.student.college_selector.MySQLiteHelper.COLUMN_REQUIREMENT;
import static com.inti.student.college_selector.MySQLiteHelper.TABLE_COMMENTS;

/**
 * Created by WongYeeKhang on 29/11/2017.
 */

public class CollegesDataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColums = {MySQLiteHelper.COLUMN_ID,
        COLUMN_NAME,MySQLiteHelper.COLUMN_COURSE_NAME,MySQLiteHelper.COLUMN_COURSE_TYPE,COLUMN_REQUIREMENT,MySQLiteHelper.COLUMN_REQUIREMENT_GRADE,
            MySQLiteHelper.COLUMN_COURSE_DURATION};

    public CollegesDataSource(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public boolean checkRecord(){
        boolean rValue = false;
        Cursor cursor = database.query(TABLE_COMMENTS,
                allColums,null,null,null,null,null);

        if (cursor.moveToFirst()) {
            do {
                // Data Is Exist;
                rValue = true;
            } while (cursor.moveToNext());
        }

        return rValue;
    }

    public void insertRecord(String college_name,String course_name,String course_type,String requirement,String requirment_grade,String duration) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,college_name);
        values.put(MySQLiteHelper.COLUMN_COURSE_NAME,course_name);
        values.put(MySQLiteHelper.COLUMN_COURSE_TYPE,course_type);
        values.put(COLUMN_REQUIREMENT,requirement);
        values.put(MySQLiteHelper.COLUMN_REQUIREMENT_GRADE,requirment_grade);
        values.put(MySQLiteHelper.COLUMN_COURSE_DURATION,duration);
        database.insert(TABLE_COMMENTS, null, values);
    }


    public List<College> get_searchCollege(String course_type){
        List<College>colleges = new ArrayList<College>();

        Log.d("check1","MySQLiteHelper.COLUMN_NAME:"+ COLUMN_NAME);
        Log.d("check1","course_type:"+course_type);

        Cursor cursor = database.query(TABLE_COMMENTS,allColums,
                COLUMN_COURSE_TYPE + "='" +course_type+ "'",null,null,null,null);

        Log.d("check2","column_name:"+ COLUMN_NAME);

        if (cursor != null && cursor.moveToFirst()){
            Log.d("check2","is_cursor");
            String name = cursor.getString( cursor.getColumnIndex("name") );
            Log.d("check2","is_cursor_name:"+name);
        }
        else
        {
            Log.d("check2","!is_cursor");
        }

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String name = cursor.getString( cursor.getColumnIndex("name") );
            Log.d("check21","is_cursor_name:"+name);
            College college = cursorToComment(cursor);
            colleges.add(college);
            cursor.moveToNext();
        }


        cursor.close();
        return colleges;
    }


    private College cursorToComment (Cursor cursor) {
        College college = new College ();
        college.setId(cursor.getLong(0));
        college.setCollege_name(cursor.getString(1));
        college.setCourse_name(cursor.getString(2));
        college.setCourse_requirement(cursor.getString(4));
        college.setCourse_requirement_grade(cursor.getString(5));
        college.setCourse_duration(cursor.getString(6));
        return college;
    }
}
