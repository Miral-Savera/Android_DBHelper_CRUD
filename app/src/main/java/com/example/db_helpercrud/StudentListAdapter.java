package com.example.db_helpercrud;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class StudentListAdapter extends ArrayAdapter<Student> {

    private Context context;
    int studentlist;
    List<Student> students;

    public StudentListAdapter(@NonNull Context context,int studentlist,List<Student> students) {
        super(context,R.layout.student_list,students);
        this.context = context;
        this.studentlist = studentlist;
        this.students = students;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view  = layoutInflater.inflate(R.layout.student_list,null);

        final Student stud = students.get(position);

        TextView tv_name = (TextView)view.findViewById(R.id.name);
        TextView tv_age = view.findViewById(R.id.age);
        TextView tv_city = view.findViewById(R.id.city);
        Button delete_student_btn = view.findViewById(R.id.delete_student);
        Button edit_student_btn = view.findViewById(R.id.edit_student);

        tv_name.setText(students.get(position).getName());
        tv_age.setText(String.valueOf(students.get(position).getAge()));
        tv_city.setText(students.get(position).getCity());

        delete_student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are You Sure ? ");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DB_Helper db = new DB_Helper(context);
                        db.deleteStudent(stud.getStudentid());
                        displayStudents();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        edit_student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudent(stud);
            }
        });

        return view;
    }

    private void updateStudent(final Student student) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.update_student_dialogue,null);
        builder.setView(view);

        final EditText editname = view.findViewById(R.id.name);
        final EditText editage = view.findViewById(R.id.age);
        final Spinner editcity = view.findViewById(R.id.city);

        editname.setText(student.getName());
        editage.setText(String.valueOf(student.getAge()));

        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.update_student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editname.getText().toString();
                int age = Integer.parseInt(editage.getText().toString());
                String city = editcity.getSelectedItem().toString();
                int studentid = student.getStudentid();

                Student s = new Student(studentid,name,age,city);
                DB_Helper db = new DB_Helper(context);
                db.updateStudent(s);
                displayStudents();
                dialog.dismiss();
            }
        });

    }

    public void displayStudents(){
        DB_Helper db = new DB_Helper(context);
        Cursor c = db.viewStudent();
        if (c.moveToFirst()){
            students.clear();
            do{
                students.add(new Student(
                        c.getInt(0),
                        c.getString(1),
                        c.getInt(2),
                        c.getString(3)
                ));
            }while (c.moveToNext());
        }
        c.close();
        notifyDataSetChanged();
    }


}
