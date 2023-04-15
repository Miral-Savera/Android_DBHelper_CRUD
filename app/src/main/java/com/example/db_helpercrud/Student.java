package com.example.db_helpercrud;

public class Student {

    private int studentid;
    private String name;
    private int age;
    private String city;

    public Student(int studentid, String name, int age, String city) {
        this.studentid = studentid;
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public Student(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
