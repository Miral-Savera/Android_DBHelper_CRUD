package com.example.db_helpercrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_Helper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "StudentDB";
    public static final String TABLE_NAME = "students";


    public DB_Helper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE "+TABLE_NAME+"(studentid AUTOINCREMENT PRIMARY KEY,name VARCHAR(255) NOT NULL,age INT(11) NOT NULL,city VARCHAR(255) NOT NULL)");
        db.execSQL("CREATE TABLE students (\n" +
                "    studentid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    name varchar(255) NOT NULL,\n" +
                "    age int,\n" +
                "    city varchar(100)\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertStudent(Student student){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",student.getName());
        cv.put("age",student.getAge());
        cv.put("city",student.getCity());
        long result = db.insert(TABLE_NAME,null,cv);
        db.close();
        if (result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean updateStudent(Student student){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",student.getName());
        cv.put("age",student.getAge());
        cv.put("city",student.getCity());
        int result = db.update(TABLE_NAME,cv,"studentid = ?",new String[]{String.valueOf(student.getStudentid())});
        db.close();
        if (result > 0){
            return  true;
        }
        else{
            return false;
        }
    }

    public boolean deleteStudent(int studentid){
        SQLiteDatabase db = getWritableDatabase();
        int result = db.delete(TABLE_NAME,"studentid = ?",new String[]{String.valueOf(studentid)});
        db.close();
        if (result > 0){
            return true;
        }
        else{
            return false;
        }
    }

    public Cursor viewStudent(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null,null);
        return cursor;
    }

}
