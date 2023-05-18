package com.crud.enrollment.model;

import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teachers")
public class Teacher {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "position")
    private String position;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;
    
    public Teacher(String position) {
        this.position = position;
    }

    public Teacher(String position, User user) {
        this.position = position;
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
