package com.gradebook.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Test {

    @Id
    @GeneratedValue
    @JsonProperty("test_id")
    private int testId;

    @JsonProperty("test_name")
    private String testName;

    @JsonProperty("test_subject")
    private String testSubject;

    @Min(value=1, message="Test needs to have at least 1 question.")
    @JsonProperty("number_of_questions")
    private int numberOfQuestions;

    @OneToMany(mappedBy = "test")
    private Set<StudentTest> studentTests = new HashSet<>();

    public Test() {
    }

    public Test(int testId) {
        this.testId = testId;
    }

    public Test(String testName, String testSubject, int numberOfQuestions) {
        this.testName = testName;
        this.testSubject = testSubject;
        this.numberOfQuestions = numberOfQuestions;
    }

    public Test(int testId, String testName, String testSubject, int numberOfQuestions) {
        this.testId = testId;
        this.testName = testName;
        this.testSubject = testSubject;
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestSubject() {
        return testSubject;
    }

    public void setTestSubject(String testSubject) {
        this.testSubject = testSubject;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public Set<StudentTest> getStudentTests() {
        return studentTests;
    }

    public void setStudentTests(Set<StudentTest> studentTests) {
        this.studentTests = studentTests;
    }

}
