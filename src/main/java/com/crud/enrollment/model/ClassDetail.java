package com.crud.enrollment.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "class_detail")
public class ClassDetail {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "time")
    private String time;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable=false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable=false)
    private Teacher teacher;

    @JsonIgnore
    @OneToOne(mappedBy = "class1", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Classm class1;
    
    public ClassDetail(String code, String name, String time) {
        this.code = code;
        this.name = name;
        this.time = time;
    }

    public ClassDetail(String code, String name, String time, Subject subject, Teacher teacher) {
        this.code = code;
        this.name = name;
        this.time = time;
        this.subject = subject;
        this.teacher = teacher;
    }
}
