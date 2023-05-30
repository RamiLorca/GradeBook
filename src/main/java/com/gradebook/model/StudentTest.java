package com.gradebook.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class StudentTest {

    @EmbeddedId
    private StudentTestKey studentTestKey;

    @JsonProperty("student_id")
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @JsonProperty("test_id")
    @ManyToOne
    @MapsId("testId")
    @JoinColumn(name = "test_id")
    private Test test;

    @JsonProperty("right_answers")
    private int rightAnswers;

    @JsonProperty("date_taken")
    private LocalDate dateTaken;

    @JsonProperty("grade_percent")
    private int gradePercent;

    public StudentTest() {
    }

    public StudentTest(int studentId, int testId, int rightAnswers, LocalDate dateTaken) {
        this.student = new Student();
        this.student.setStudentId(studentId);
        this.test = new Test();
        this.test.setTestId(testId);
        this.rightAnswers = rightAnswers;
        this.dateTaken = dateTaken;
        this.studentTestKey = new StudentTestKey(studentId, testId);
    }

    public StudentTest(StudentTestKey studentTestKey, Student student, Test test, int rightAnswers, LocalDate dateTaken) {
        this.studentTestKey = studentTestKey;
        this.student = student;
        this.test = test;
        this.rightAnswers = rightAnswers;
        this.dateTaken = dateTaken;
    }

//    public StudentTest(Student student, Test test, int rightAnswers, LocalDate dateTaken) {
//        this.student = student;
//        this.test = test;
//        this.rightAnswers = rightAnswers;
//        this.dateTaken = dateTaken;
//        this.studentTestKey = new StudentTestKey(student.getStudentId(), test.getTestId());
//    }

    public StudentTest(Student student, Test test, int rightAnswers, LocalDate dateTaken) {
        this.student = student;
        this.test = test;
        this.rightAnswers = rightAnswers;
        this.dateTaken = dateTaken;
        this.student.setStudentId(student.getStudentId()); // Set the studentId in the Student object
        this.test.setTestId(test.getTestId()); // Set the testId in the Test object
    }

    public StudentTestKey getStudentTestKey() {
        return studentTestKey;
    }

    public void setStudentTestKey(StudentTestKey studentTestKey) {
        this.studentTestKey = studentTestKey;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public int getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(int rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public LocalDate getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(LocalDate dateTaken) {
        this.dateTaken = dateTaken;
    }

    public int getGradePercent() {
        return gradePercent;
    }

    public void setGradePercent(int gradePercent) {
        this.gradePercent = gradePercent;
    }

    public int calculateGradePercent(int rightAnswers, int numberOfQuestions) {
        double percentage = ((double) rightAnswers / numberOfQuestions) * 100;
        return (int) Math.round(percentage);
    }

}
