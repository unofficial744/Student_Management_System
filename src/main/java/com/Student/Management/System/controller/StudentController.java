package com.Student.Management.System.controller;

import com.Student.Management.System.entity.Student;
import com.Student.Management.System.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        student.setId(null); // ensure id is auto-generated
        Student saved = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found with id " + id);
        }
        studentRepository.deleteById(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}
