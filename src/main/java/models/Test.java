package models;

import java.time.LocalDate;

public class Test {

    private String testId;
    private String studentId;
    private String subject;
    private LocalDate dateTaken;
    private int Questions;
    private int correctAnswers;


    public Test() {
    }

    public Test(String studentId, String subject, LocalDate dateTaken, int questions) {
        this.studentId = studentId;
        this.subject = subject;
        this.dateTaken = dateTaken;
        Questions = questions;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LocalDate getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(LocalDate dateTaken) {
        this.dateTaken = dateTaken;
    }

    public int getQuestions() {
        return Questions;
    }

    public void setQuestions(int questions) {
        Questions = questions;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}
