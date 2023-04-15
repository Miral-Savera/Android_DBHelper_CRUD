package com.example.db_helpercrud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView studentls;
    Button add_student;
    DB_Helper db_helper;
    List<Student> studentslist;
    StudentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentls = findViewById(R.id.student_list);
        add_student = findViewById(R.id.add_stud_btn);
        studentslist = new ArrayList<>();
        displayStudents();

        add_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add_student_page = new Intent(MainActivity.this,AddStudent.class);
                startActivity(add_student_page);
            }
        });

    }

    public void displayStudents(){
        DB_Helper db = new DB_Helper(this);
        Cursor c = db.viewStudent();
        if (c.moveToFirst()){
            studentslist.clear();
            do{
                studentslist.add(new Student(
                    c.getInt(0),
                    c.getString(1),
                    c.getInt(2),
                    c.getString(3)
                ));
            }while (c.moveToNext());
        }
        adapter = new StudentListAdapter(this,R.layout.student_list,studentslist);
        studentls.setAdapter(adapter);
    }

}