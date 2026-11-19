package com.example.studentmanagement.model;

import java.util.List;

public class Student {

    private int id;
    private String name;
    private String email;
    private String course;
    private double programmingScore;
    private double mathematicsScore;
    private List<String> skills;

    public Student() {
    }

    public Student(int id, String name, String email,
                   String course, double programmingScore,
                   double mathematicsScore, List<String> skills) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.course = course;
        this.programmingScore = programmingScore;
        this.mathematicsScore = mathematicsScore;
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getProgrammingScore() {
        return programmingScore;
    }

    public void setProgrammingScore(double programmingScore) {
        this.programmingScore = programmingScore;
    }

    public double getMathematicsScore() {
        return mathematicsScore;
    }

    public void setMathematicsScore(double mathematicsScore) {
        this.mathematicsScore = mathematicsScore;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public double getAverage() {
        return (programmingScore + mathematicsScore) / 2;
    }

    public String getCategory() {

        double avg = getAverage();

        if (avg >= 85)
            return "Excellent";

        else if (avg >= 70)
            return "Good";

        else if (avg >= 50)
            return "Satisfactory";

        else
            return "Needs Improvement";
    }

    public String getStrongerSubject() {

        if (programmingScore > mathematicsScore)
            return "Programming";

        else if (mathematicsScore > programmingScore)
            return "Mathematics";

        else
            return "Both Equal";
    }
}