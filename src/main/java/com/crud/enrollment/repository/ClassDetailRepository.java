package com.crud.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.enrollment.model.ClassDetail;

import java.util.List;

public interface ClassDetailRepository extends JpaRepository<ClassDetail, Long> {
    List<ClassDetail> findById(int id);
    List<ClassDetail> findByCode(int code);
    List<ClassDetail> findByName(String name);
    List<ClassDetail> findByTime(String time);
}

