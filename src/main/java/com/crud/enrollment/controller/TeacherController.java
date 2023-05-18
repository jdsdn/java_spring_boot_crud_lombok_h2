package com.crud.enrollment.controller;

import java.util.*;

import com.crud.enrollment.model.User;
import com.crud.enrollment.model.Teacher;
import com.crud.enrollment.repository.UserRepository;
import com.crud.enrollment.repository.TeacherRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TeacherController {

    UserRepository userRepository;
    TeacherRepository teacherRepository;

    @PostMapping("/teachers")
    public ResponseEntity<Teacher> createStudent(@RequestBody Map<String, Object> payload) {
        try {
            /*
                {
                    "age":11,
                    "fname":"first",
                    "lname":"last",
                    "gender":"male",
                    "position":"CS Professor"
                }
            */

            User user = new User((int)payload.get("age"), payload.get("fname").toString(), payload.get("lname").toString(), payload.get("gender").toString());
            Teacher teacher = teacherRepository.save(new Teacher(payload.get("fname").toString(), user));
            user.setTeacher(teacher);
            userRepository.save(user);

            return new ResponseEntity<>(teacher, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getSubjectById(@PathVariable("id") long id) {
        Optional<Teacher> teacherData = teacherRepository.findById(id);

        if (teacherData.isPresent()) {
            return new ResponseEntity<>(teacherData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllTutorials() {
        try {
            List<Teacher> teachers = new ArrayList<Teacher>();

            teacherRepository.findAll().forEach(teachers::add);

            if (teachers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(teachers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
