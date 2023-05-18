package com.crud.enrollment.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "class_id")
    private Classm classList;

    @Column(name = "cyear")
    private int year;

    @Column(name = "course")
    private String course;

    public Student(int year, String course) {
        this.year = year;
        this.course = course;
    }

    public Student(int year, String course, User user) {
        this.year = year;
        this.course = course;
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    @JsonIgnore
    @JsonBackReference
    public Classm getClass_list() {
        return this.classList;
    }
}
