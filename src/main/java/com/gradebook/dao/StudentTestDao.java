package com.gradebook.dao;

import com.gradebook.model.StudentTest;
import java.time.LocalDate;
import java.util.List;

public interface StudentTestDao {

    public List<StudentTest> getStudentTests();

    public boolean addStudentTest(int studentId, int testId, int rightAnswers, LocalDate dateTaken);

    public List<StudentTest> getStudentTestsByTestId(int testId);

    public List<StudentTest> getStudentTestsByStudentId(int studentId);

    public StudentTest getStudentTest(int studentId, int testId);

    public boolean updateStudentTest(int studentId, int testId, int rightAnswers);

    public boolean deleteStudentTest(int studentId, int testId);

    public boolean deleteAllStudentTestsByStudentId(int studentId);

    public boolean deleteAllStudentTestsByTestId(int testId);

}
