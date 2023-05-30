package com.gradebook.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentTestKey implements Serializable {

    @Column(name = "student_id")
    int studentId;

    @Column(name = "test_id")
    int testId;

    public StudentTestKey() {

    }

    public StudentTestKey(int studentId, int testId) {
        this.studentId = studentId;
        this.testId = testId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, testId);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        StudentTestKey other = (StudentTestKey) obj;
        return studentId == other.studentId && testId == other.testId;
    }

}
