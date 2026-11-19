package com.example.studentmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    // 1. ADD STUDENT
    public String addStudent(Student s) {

        validate(s);

        if (repository.findById(s.getId()) != null) {
            return "Student ID already exists";
        }

        repository.save(s);

        return "Student added successfully";
    }

    // 2. VALIDATION
    private void validate(Student s) {

        if (s.getId() <= 0)
            throw new IllegalArgumentException("Invalid ID");

        if (s.getName() == null || s.getName().isBlank())
            throw new IllegalArgumentException("Name is required");

        if (s.getEmail() == null || !s.getEmail().contains("@"))
            throw new IllegalArgumentException("Invalid email");

        if (s.getCourse() == null || s.getCourse().isBlank())
            throw new IllegalArgumentException("Course is required");

        if (s.getProgrammingScore() < 0 ||
            s.getProgrammingScore() > 100 ||
            s.getMathematicsScore() < 0 ||
            s.getMathematicsScore() > 100) {

            throw new IllegalArgumentException(
                "Scores must be between 0 and 100");
        }

        if (s.getSkills() == null)
            s.setSkills(new ArrayList<>());
    }

    // 3. GET ALL STUDENTS
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // 4. GET STUDENT BY ID
    public Student getStudent(int id) {
        return repository.findById(id);
    }

    // 5. SEARCH BY EMAIL
    public List<Student> searchByEmail(String email) {
        return repository.findByEmail(email);
    }

    // 6. SEARCH BY COURSE
    public List<Student> searchByCourse(String course) {
        return repository.findByCourse(course);
    }

    // 7. UPDATE STUDENT
    public String updateStudent(int id, Student s) {

        if (repository.findById(id) == null)
            return "Student not found";

        s.setId(id);

        validate(s);

        repository.update(s);

        return "Student updated successfully";
    }

    // 8. DELETE STUDENT
    public String deleteStudent(int id) {

        if (repository.findById(id) == null)
            return "Student not found";

        repository.delete(id);

        return "Student deleted successfully";
    }

    // 9. MERIT LIST - DESCENDING ORDER
    public List<Student> getMeritList() {

        List<Student> students = repository.findAll();

        students.sort((s1, s2) ->
            Double.compare(
                s2.getAverage(),
                s1.getAverage()
            )
        );

        return students;
    }

    // 10. STUDENTS NEEDING PROGRAMMING IMPROVEMENT
    public List<Student> getImprovementStudents() {

        List<Student> result = new ArrayList<>();

        for (Student s : repository.findAll()) {

            if (s.getProgrammingScore() < 50 &&
                s.getMathematicsScore() >= 75) {

                result.add(s);
            }
        }

        return result;
    }

    // 11. STUDENTS HAVING SAME TECHNICAL SKILLS
    public List<Student> getSameSkills(int id) {

        Student target = repository.findById(id);

        if (target == null)
            return new ArrayList<>();

        List<Student> result = new ArrayList<>();

        for (Student s : repository.findAll()) {

            if (s.getId() != id &&
                s.getSkills().containsAll(target.getSkills()) &&
                target.getSkills().containsAll(s.getSkills())) {

                result.add(s);
            }
        }

        return result;
    }
}