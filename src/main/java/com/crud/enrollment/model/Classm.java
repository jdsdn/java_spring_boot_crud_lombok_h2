package com.crud.enrollment.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "class")
public class Classm {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "class_id")
    @MapsId
    private ClassDetail class1;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "classList", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Student> classList = new ArrayList<Student>();

    public Classm(ClassDetail class1) {
        this.class1 = class1;
    }

    public List<Student> getClassList() {
        return classList;
    }
    public void setClassList(List<Student> class_list) {
        this.classList = class_list;
    }
}
