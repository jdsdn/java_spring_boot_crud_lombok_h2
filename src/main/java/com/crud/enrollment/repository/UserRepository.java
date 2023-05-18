package com.crud.enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.enrollment.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByAge(int age);
    List<User> findByLname(String lname);
    List<User> findByFname(String fname);
    List<User> findByGender(String gender);
}
