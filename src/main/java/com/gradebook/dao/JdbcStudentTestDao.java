package com.gradebook.dao;

import com.gradebook.model.Student;
import com.gradebook.model.StudentTest;
import com.gradebook.model.Test;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcStudentTestDao implements StudentTestDao {

    private JdbcTemplate template;
    public JdbcStudentTestDao(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<StudentTest> getStudentTests() {
        String getStudentTestsSql = "SELECT student_test.test_id AS testId, test.test_name AS testName, test.test_subject AS testSubject, " +
                "student_test.student_id AS studentId, student.first_name AS firstName, student.last_name AS lastName, student_test.right_answers " +
                "AS rightAnswers, test.number_of_questions AS numberOfQuestions, student_test.date_taken AS dateTaken " +
                "FROM student_test " +
                "JOIN student ON student_test.student_id = student.student_id " +
                "JOIN test ON student_test.test_id = test.test_id ";

        List<StudentTest> allStudentTests = new ArrayList<>();

        SqlRowSet results = template.queryForRowSet(getStudentTestsSql);

        while(results.next()){
            int retrievedTestId = results.getInt("testId");
            String testName = results.getString("testName");
            String testSubject = results.getString("testSubject");
            int retrievedStudentId = results.getInt("studentId");
            String firstName = results.getString("firstName");
            String lastName = results.getString("lastName");
            int rightAnswers = results.getInt("rightAnswers");
            int numberOfQuestions = results.getInt("numberOfQuestions");
            LocalDate dateTaken = results.getDate("dateTaken").toLocalDate();

            Student student = new Student();
            student.setStudentId(retrievedStudentId);
            student.setFirstName(firstName);
            student.setLastName(lastName);

            Test test = new Test();
            test.setTestId(retrievedTestId);
            test.setTestName(testName);
            test.setTestSubject(testSubject);
            test.setNumberOfQuestions(numberOfQuestions);

            StudentTest studentTest = new StudentTest();
            studentTest.setStudent(student);
            studentTest.setTest(test);
            studentTest.setRightAnswers(rightAnswers);
            studentTest.setDateTaken(dateTaken);

            allStudentTests.add(studentTest);
        }
        return allStudentTests;
    }

    @Override
    public boolean addStudentTest(int studentId, int testId, int rightAnswers, LocalDate dateTaken) {

        if(!isValidId(studentId) || !isValidId(testId) || rightAnswers < 0) {
            return false;
        }

        String addStudentTestSql = "INSERT INTO student_test (student_id, test_id, right_answers, date_taken) VALUES (?, ?, ?, ?)";
        try {
            template.update(addStudentTestSql, studentId, testId, rightAnswers, dateTaken);
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<StudentTest> getStudentTestsByTestId(int testId) {
        String getStudentTestsByTestIdSql = "SELECT student_test.test_id AS testId, test.test_name AS testName, test.test_subject AS testSubject, " +
                "student_test.student_id AS studentId, student.first_name AS firstName, student.last_name AS lastName, student_test.right_answers " +
                "AS rightAnswers, test.number_of_questions AS numberOfQuestions, student_test.date_taken AS dateTaken " +
                "FROM student_test " +
                "JOIN student ON student_test.student_id = student.student_id " +
                "JOIN test ON student_test.test_id = test.test_id " +
                "WHERE student_test.test_id = ? " +
                "ORDER BY student.last_name ASC, student.first_name ASC";

        List<StudentTest> allStudentTestsById = new ArrayList<>();

        SqlRowSet results = template.queryForRowSet(getStudentTestsByTestIdSql, testId);

        while(results.next()){
            int retrievedTestId = results.getInt("testId");
            String testName = results.getString("testName");
            String testSubject = results.getString("testSubject");
            int retrievedStudentId = results.getInt("studentId");
            String firstName = results.getString("firstName");
            String lastName = results.getString("lastName");
            int rightAnswers = results.getInt("rightAnswers");
            int numberOfQuestions = results.getInt("numberOfQuestions");
            LocalDate dateTaken = results.getDate("dateTaken").toLocalDate();

            Student student = new Student();
            student.setStudentId(retrievedStudentId);
            student.setFirstName(firstName);
            student.setLastName(lastName);

            Test test = new Test();
            test.setTestId(retrievedTestId);
            test.setTestName(testName);
            test.setTestSubject(testSubject);
            test.setNumberOfQuestions(numberOfQuestions);

            StudentTest studentTest = new StudentTest();
            studentTest.setStudent(student);
            studentTest.setTest(test);
            studentTest.setRightAnswers(rightAnswers);
            studentTest.setDateTaken(dateTaken);

            allStudentTestsById.add(studentTest);
        }
        return allStudentTestsById;
    }

    @Override
    public List<StudentTest> getStudentTestsByStudentId(int studentId) {
        String getStudentTestsByStudentIdSql = "SELECT student_test.test_id AS testId, test.test_name AS testName, test.test_subject AS testSubject, " +
                "student_test.right_answers AS rightAnswers, test.number_of_questions AS numberOfQuestions, student_test.date_taken AS dateTaken " +
                "FROM student_test " +
                "JOIN student ON student_test.student_id = student.student_id " +
                "JOIN test ON student_test.test_id = test.test_id " +
                "WHERE student_test.student_id = ? " +
                "ORDER BY student_test.date_taken ASC";

        List<StudentTest> allStudentTestsById = new ArrayList<>();

        SqlRowSet results = template.queryForRowSet(getStudentTestsByStudentIdSql, studentId);

        while(results.next()){
            int retrievedTestId = results.getInt("testId");
            String testName = results.getString("testName");
            String testSubject = results.getString("testSubject");
            int rightAnswers = results.getInt("rightAnswers");
            int numberOfQuestions = results.getInt("numberOfQuestions");
            LocalDate dateTaken = results.getDate("dateTaken").toLocalDate();

            Test test = new Test();
            test.setTestId(retrievedTestId);
            test.setTestName(testName);
            test.setTestSubject(testSubject);
            test.setNumberOfQuestions(numberOfQuestions);

            StudentTest studentTest = new StudentTest();
            studentTest.setTest(test);
            studentTest.setRightAnswers(rightAnswers);
            studentTest.setDateTaken(dateTaken);

            allStudentTestsById.add(studentTest);
        }
        return allStudentTestsById;
    }

    @Override
    public StudentTest getStudentTest(int studentId, int testId) {
        String getStudentTestSql = "SELECT student_test.test_id AS testId, test.test_name AS testName, test.test_subject AS testSubject, " +
                "student_test.student_id AS studentId, student.first_name AS firstName, student.last_name AS lastName, student_test.right_answers " +
                "AS rightAnswers, test.number_of_questions AS numberOfQuestions, student_test.date_taken AS dateTaken " +
                "FROM student_test " +
                "JOIN student ON student_test.student_id = student.student_id " +
                "JOIN test ON student_test.test_id = test.test_id " +
                "WHERE student_test.test_id = ? AND student_test.student_id = ?";

        try {
            return template.queryForObject(getStudentTestSql, new Object[]{testId, studentId}, (resultSet, rowNum) -> {
                Student student = new Student();
                student.setStudentId(resultSet.getInt("studentId"));
                student.setFirstName(resultSet.getString("firstName"));
                student.setLastName(resultSet.getString("lastName"));

                Test test = new Test();
                test.setTestId(resultSet.getInt("testId"));
                test.setTestName(resultSet.getString("testName"));
                test.setTestSubject(resultSet.getString("testSubject"));
                test.setNumberOfQuestions(resultSet.getInt("numberOfQuestions"));

                StudentTest studentTest = new StudentTest();
                studentTest.setStudent(student);
                studentTest.setTest(test);
                studentTest.setRightAnswers(resultSet.getInt("rightAnswers"));
                studentTest.setDateTaken(resultSet.getDate("dateTaken").toLocalDate());

                int gradePercent = studentTest.calculateGradePercent(studentTest.getRightAnswers(), test.getNumberOfQuestions());
                studentTest.setGradePercent(gradePercent);

                return studentTest;
            });
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean updateStudentTest(int studentId, int testId, int rightAnswers) {
        try {
            String updateStudentTestSql = "UPDATE student_test SET right_answers = ? WHERE test_id = ? AND student_id = ?";
            int rowsAffected = template.update(updateStudentTestSql, rightAnswers, testId, studentId);
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            System.out.println("Error updating student test: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStudentTest(int studentId, int testId) {
        try {
            String deleteStudentTestSql = "DELETE FROM student_test WHERE student_id = ? AND test_id = ?";
            int rowsAffected = template.update(deleteStudentTestSql, studentId, testId);
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            System.out.println("Error deleting student test: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAllStudentTestsByStudentId(int studentId) {
        try {
            String deleteAllStudentTestsSql = "DELETE FROM student_test WHERE student_id = ?";
            int rowsAffected = template.update(deleteAllStudentTestsSql, studentId);
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            System.out.println("Error deleting student tests: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAllStudentTestsByTestId(int testId) {
        try {
            String deleteAllStudentTestsSql = "DELETE FROM student_test WHERE test_id = ?";
            int rowsAffected = template.update(deleteAllStudentTestsSql, testId);
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            System.out.println("Error deleting student tests: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private boolean isValidId(int Id) {
        return Id > 0 && NumberUtils.isDigits(String.valueOf(Id));
    }

}
