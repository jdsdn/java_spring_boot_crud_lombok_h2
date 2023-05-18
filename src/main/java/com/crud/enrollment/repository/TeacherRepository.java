package com.crud.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.enrollment.model.Teacher;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findById(int id);
    List<Teacher> findByPosition(String position);
}
