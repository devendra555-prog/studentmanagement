package com.example.studentmanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // ADD STUDENT
    @PostMapping
    public String addStudent(@RequestBody Student s) {
        return service.addStudent(s);
    }

    // GET ALL STUDENTS
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    // GET STUDENT BY ID
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {
        return service.getStudent(id);
    }

    // UPDATE STUDENT
    @PutMapping("/{id}")
    public String updateStudent(
            @PathVariable int id,
            @RequestBody Student s) {

        return service.updateStudent(id, s);
    }

    // DELETE STUDENT
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        return service.deleteStudent(id);
    }

    // MERIT LIST
    @GetMapping("/merit")
    public List<Student> meritList() {
        return service.getMeritList();
    }

    // SEARCH BY EMAIL
    @GetMapping("/search/email")
    public List<Student> searchEmail(@RequestParam String email) {
        return service.searchByEmail(email);
    }

    // SEARCH BY COURSE
    @GetMapping("/search/course")
    public List<Student> searchCourse(@RequestParam String course) {
        return service.searchByCourse(course);
    }

    // IMPROVEMENT STUDENTS
    @GetMapping("/improvement")
    public List<Student> improvementStudents() {
        return service.getImprovementStudents();
    }

    // SAME SKILLS
    @GetMapping("/{id}/same-skills")
    public List<Student> sameSkills(@PathVariable int id) {
        return service.getSameSkills(id);
    }
}