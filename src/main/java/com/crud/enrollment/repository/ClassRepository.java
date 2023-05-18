package com.crud.enrollment.repository;

import com.crud.enrollment.model.Classm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Classm, Long> {
    // List<ClassDetail> findByClassId(int id);
    // List<ClassDetail> findByStudentId(int id);
}
