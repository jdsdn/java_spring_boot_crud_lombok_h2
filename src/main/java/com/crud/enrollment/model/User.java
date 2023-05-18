package com.crud.enrollment.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Student student;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Teacher teacher;

    @Column(name = "age")
    private int age;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "gender")
    private String gender;

    public User(int age, String fname, String lname, String gender) {
        this.age = age;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
    }

    public User(int age, String fname, String lname, String gender, Student student) {
        this.age = age;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.student = student;
    }

    @JsonIgnore
    @JsonBackReference
    public Student getStudent() {
        return this.student;
    }

    @JsonIgnore
    @JsonBackReference
    public Teacher getTeacher() {
        return this.teacher;
    }
}
