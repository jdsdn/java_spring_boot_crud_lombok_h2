package com.crud.enrollment.controller;

import java.util.*;

import com.crud.enrollment.model.Classm;
import com.crud.enrollment.model.Student;
import com.crud.enrollment.model.Subject;
import com.crud.enrollment.model.Teacher;
import com.crud.enrollment.model.ClassDetail;
import com.crud.enrollment.repository.ClassDetailRepository;
import com.crud.enrollment.repository.ClassRepository;
import com.crud.enrollment.repository.StudentRepository;
import com.crud.enrollment.repository.SubjectRepository;
import com.crud.enrollment.repository.TeacherRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ClassDetailController {

    ClassRepository classRepository;
    StudentRepository studentRepository;
    SubjectRepository subjectRepository;
    TeacherRepository teacherRepository;
    ClassDetailRepository classDetailRepository;

    @PostMapping("/classes")
    public ResponseEntity<ClassDetail> createClass(@RequestBody Map<String, Object> payload) {
        try {
            /*
                {
                    "code":"Cs111",
                    "name":"first",
                    "time":"last",
                    "subject_id":1,
                    "teacher_id":1
                }
            */

            Optional<Subject> subject = subjectRepository.findById(((Number) payload.get("subject_id")).longValue());
            Optional<Teacher> teacher = teacherRepository.findById(((Number) payload.get("teacher_id")).longValue());
            ClassDetail classDetail = classDetailRepository.save(new ClassDetail(payload.get("code").toString(), payload.get("name").toString(), payload.get("time").toString(), subject.get(), teacher.get()));

            return new ResponseEntity<>(classDetail, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("/classes/enroll")
    public ResponseEntity<Classm> enrollStudent(@RequestBody Map<String, Object> payload) {
        try {
            /*
                {
                    "class_id":1,
                    "student_id":1
                }
            */

            Classm classm;
            Optional<Student> student = studentRepository.findById(((Number) payload.get("student_id")).longValue());
            Optional<ClassDetail> cls = classDetailRepository.findById(((Number) payload.get("class_id")).longValue());

            if(classRepository.existsById(((Number) payload.get("class_id")).longValue())) {
                classm = classRepository.findById(((Number) payload.get("class_id")).longValue()).get();
            } else {
                classm = new Classm(cls.get());
            }

            classm.getClassList().add(student.get());
            student.get().setClassList(classm);

            Classm classave = classRepository.save(classm);

            return new ResponseEntity<>(classave, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/classes")
    public ResponseEntity<List<ClassDetail>> getAllTutorials() {
        try {
            List<ClassDetail> classes = new ArrayList<ClassDetail>();

            classDetailRepository.findAll().forEach(classes::add);

            if (classes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(classes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/classes/enrolled")
    public ResponseEntity<List<Classm>> getAllEnrolled() {
        try {
            List<Classm> classes = new ArrayList<Classm>();

            classRepository.findAll().forEach(classes::add);

            if (classes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(classes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/classes/{id}")
    public ResponseEntity<ClassDetail> getSubjectById(@PathVariable("id") long id) {
        Optional<ClassDetail> classesdetailData = classDetailRepository.findById(id);

        if (classesdetailData.isPresent()) {
            return new ResponseEntity<>(classesdetailData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
