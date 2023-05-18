package com.crud.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.enrollment.model.Subject;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByCode(String code);
    List<Subject> findByNameContaining(String name);
}
