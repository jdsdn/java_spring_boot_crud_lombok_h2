package com.crud.enrollment.controller;

import java.util.*;

import com.crud.enrollment.model.User;
import com.crud.enrollment.model.Student;
import com.crud.enrollment.repository.UserRepository;
import com.crud.enrollment.repository.StudentRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StudentController {

    UserRepository userRepository;
    StudentRepository studentRepository;

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Map<String, Object> payload) {
        try {
            /*
                {
                    "age":11,
                    "fname":"first",
                    "lname":"last",
                    "gender":"male",
                    "year":1,
                    "course":"BSCS"
                }
            */

            // int year, String course, User user
            User user = new User((int)payload.get("age"), payload.get("fname").toString(), payload.get("lname").toString(), payload.get("gender").toString());
            Student student = studentRepository.save(new Student((int)payload.get("year"), payload.get("course").toString(), user));
            user.setStudent(student);
            userRepository.save(user);

            return new ResponseEntity<>(student, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getSubjectById(@PathVariable("id") long id) {
        Optional<Student> studentData = studentRepository.findById(id);

        if (studentData.isPresent()) {
            return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllTutorials() {
        try {
            List<Student> students = new ArrayList<Student>();

            studentRepository.findAll().forEach(students::add);

            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
