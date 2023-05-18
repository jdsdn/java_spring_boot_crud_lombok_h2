package com.crud.enrollment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.crud.enrollment.model.Subject;
import com.crud.enrollment.repository.SubjectRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SubjectController {
    
    SubjectRepository subjectRepository;

    @GetMapping("/subjects")
    public ResponseEntity<List<Subject>> getAllSubjects(@RequestParam(required = false) String name) {
        try {
            List<Subject> Subjects = new ArrayList<Subject>();

            if (name == null)
                subjectRepository.findAll().forEach(Subjects::add);
            else
                subjectRepository.findByNameContaining(name).forEach(Subjects::add);

            if (Subjects.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(Subjects, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable("id") long id) {
        Optional<Subject> subjectData = subjectRepository.findById(id);

        if (subjectData.isPresent()) {
            return new ResponseEntity<>(subjectData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/subjects")
    public ResponseEntity<Subject> createTutorial(@RequestBody Subject subject) {
        try {
            Subject _subject = subjectRepository.save(new Subject(subject.getCode(), subject.getName()));
            return new ResponseEntity<>(_subject, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/subjects/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable("id") long id, @RequestBody Subject subject) {
        Optional<Subject> subjectData = subjectRepository.findById(id);

        if (subjectData.isPresent()) {
            Subject _subject = subjectData.get();
            _subject.setCode(subject.getCode());
            _subject.setName(subject.getName());
            return new ResponseEntity<>(subjectRepository.save(_subject), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<HttpStatus> deleteSubject(@PathVariable("id") long id) {
        try {
            subjectRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/subjects")
    public ResponseEntity<HttpStatus> deleteAllSubjects() {
        try {
            subjectRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/subjects/code/{code}")
    public ResponseEntity<List<Subject>> findByCode(@PathVariable("code") String code) {
        try {
            List<Subject> subjects = subjectRepository.findByCode(code);

            if (subjects.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(subjects, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
