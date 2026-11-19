package com.example.studentmanagement.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.studentmanagement.model.Student;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbc;

    public StudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // ADD STUDENT
    public int save(Student s) {

        String sql = """
            INSERT INTO students
            (id, name, email, course, programming_score,
             mathematics_score, skills)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        return jdbc.update(sql,
                s.getId(),
                s.getName(),
                s.getEmail(),
                s.getCourse(),
                s.getProgrammingScore(),
                s.getMathematicsScore(),
                String.join(",", s.getSkills()));
    }

    // GET ALL STUDENTS
    public List<Student> findAll() {

        String sql = "SELECT * FROM students";

        return jdbc.query(sql, (rs, rowNum) -> {

            Student s = new Student();

            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setEmail(rs.getString("email"));
            s.setCourse(rs.getString("course"));

            s.setProgrammingScore(
                    rs.getDouble("programming_score"));

            s.setMathematicsScore(
                    rs.getDouble("mathematics_score"));

            String skills = rs.getString("skills");

            s.setSkills(
                    skills == null || skills.isBlank()
                    ? List.of()
                    : List.of(skills.split(",")));

            return s;
        });
    }

    // SEARCH BY ID
    public Student findById(int id) {

        String sql = "SELECT * FROM students WHERE id = ?";

        List<Student> list = jdbc.query(sql, (rs, rowNum) -> {

            Student s = new Student();

            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setEmail(rs.getString("email"));
            s.setCourse(rs.getString("course"));

            s.setProgrammingScore(
                    rs.getDouble("programming_score"));

            s.setMathematicsScore(
                    rs.getDouble("mathematics_score"));

            String skills = rs.getString("skills");

            s.setSkills(
                    skills == null || skills.isBlank()
                    ? List.of()
                    : List.of(skills.split(",")));

            return s;
        }, id);

        return list.isEmpty() ? null : list.get(0);
    }

    // SEARCH BY EMAIL
    public List<Student> findByEmail(String email) {

        return jdbc.query(
            "SELECT * FROM students WHERE email = ?",
            (rs, rowNum) -> mapStudent(rs),
            email
        );
    }

    // SEARCH BY COURSE
    public List<Student> findByCourse(String course) {

        return jdbc.query(
            "SELECT * FROM students WHERE course = ?",
            (rs, rowNum) -> mapStudent(rs),
            course
        );
    }

    // COMMON ROW MAPPER
    private Student mapStudent(java.sql.ResultSet rs)
            throws java.sql.SQLException {

        Student s = new Student();

        s.setId(rs.getInt("id"));
        s.setName(rs.getString("name"));
        s.setEmail(rs.getString("email"));
        s.setCourse(rs.getString("course"));

        s.setProgrammingScore(
                rs.getDouble("programming_score"));

        s.setMathematicsScore(
                rs.getDouble("mathematics_score"));

        String skills = rs.getString("skills");

        s.setSkills(
                skills == null || skills.isBlank()
                ? List.of()
                : List.of(skills.split(",")));

        return s;
    }

    // UPDATE STUDENT
    public int update(Student s) {

        String sql = """
            UPDATE students
            SET programming_score = ?,
                mathematics_score = ?,
                skills = ?
            WHERE id = ?
        """;

        return jdbc.update(sql,
                s.getProgrammingScore(),
                s.getMathematicsScore(),
                String.join(",", s.getSkills()),
                s.getId());
    }

    // DELETE STUDENT
    public int delete(int id) {

        return jdbc.update(
            "DELETE FROM students WHERE id = ?", id);
    }
}