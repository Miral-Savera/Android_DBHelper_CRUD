package com.example.db_helpercrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddStudent extends AppCompatActivity implements View.OnClickListener {

    EditText name,age;
    Spinner city;
    Button add_student;
    SQLiteDatabase sd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        city = findViewById(R.id.city);
        add_student = findViewById(R.id.add_stud);

        add_student.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String student_name = name.getText().toString();
        int student_age = Integer.parseInt(age.getText().toString());
        String student_city = city.getSelectedItem().toString();
        Student s = new Student(student_name,student_age,student_city);
        DB_Helper db = new DB_Helper(this);
        boolean result = db.insertStudent(s);
        if (result==true){
            Intent view_list = new Intent(AddStudent.this,MainActivity.class);
            startActivity(view_list);
        }
        else {
            Toast.makeText(this, "Problem to Add New Student", Toast.LENGTH_LONG).show();
        }
    }


}