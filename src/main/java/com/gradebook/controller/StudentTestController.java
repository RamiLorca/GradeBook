package com.gradebook.controller;

import com.gradebook.dao.StudentTestDao;
import com.gradebook.model.Student;
import com.gradebook.model.StudentTest;
import com.gradebook.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class StudentTestController {

    @Autowired
    StudentTestDao studentTestDao;

    @RequestMapping(path = "/student_tests", method = RequestMethod.GET)
    public List<StudentTest> getStudentTests() {
        return studentTestDao.getStudentTests();
    }

    @RequestMapping(path = "/student_tests/add", method = RequestMethod.POST)
    public void addStudentTest(@RequestBody StudentTest studentTest) {

        Student student = studentTest.getStudent();
        int studentId = student.getStudentId();
        Test test = studentTest.getTest();
        int testId = test.getTestId();
        int rightAnswers = studentTest.getRightAnswers();
        LocalDate dateTaken = studentTest.getDateTaken();

        if (studentId <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid student ID.");
        }

        if (testId <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid test ID.");
        }

        if (rightAnswers < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid rightAnswers value.");
        }

        if (!studentTestDao.addStudentTest(studentId, testId, rightAnswers, dateTaken)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to add student test.");
        }
    }

    @RequestMapping(path = "/student_tests/test/{testId}", method = RequestMethod.GET)
    public List<StudentTest> getStudentTestsByTestId(@PathVariable Integer testId) {
        List<StudentTest> studentTests = studentTestDao.getStudentTestsByTestId(testId);

        if(studentTests == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No tests found.");
        } else {
            return studentTests;
        }
    }

    @RequestMapping(path = "/student_tests/student/{studentId}", method = RequestMethod.GET)
    public List<StudentTest> getStudentTestsByStudentId(@PathVariable Integer studentId) {
        List<StudentTest> studentTests = studentTestDao.getStudentTestsByStudentId(studentId);

        if(studentTests == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No tests found.");
        } else {
            return studentTests;
        }
    }

    @RequestMapping(path = "/student_tests/specific/{testId}/{studentId}", method = RequestMethod.GET)
    public StudentTest getStudentTest(@PathVariable("studentId") Integer studentId, @PathVariable("testId") Integer testId) {

        StudentTest studentTest = studentTestDao.getStudentTest(studentId, testId);

        if(studentTest == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No student test found.");
        } else {
            return studentTest;
        }
    }

    @RequestMapping(path = "/student_tests/specific/{testId}/{studentId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateStudentTest(@PathVariable("studentId") int studentId, @PathVariable("testId") int testId, @RequestBody Map<String, Integer> requestBody) {
        try {
            Integer rightAnswers = requestBody.get("rightAnswers");

            if (rightAnswers != null) {
                boolean success = studentTestDao.updateStudentTest(studentId, testId, rightAnswers);

                if (success) {
                    return ResponseEntity.ok("Student test updated successfully.");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update student test.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid 'rightAnswers' value provided.");
            }
        } catch (Exception e) {
            String errorMessage = "Failed to update student test. Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @RequestMapping(path = "/student_tests/specific/{testId}/{studentId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteStudentTest(@PathVariable("studentId") int studentId, @PathVariable("testId") int testId) {
        try {
            boolean success = studentTestDao.deleteStudentTest(studentId, testId);

            if (success) {
                return ResponseEntity.ok("Student test deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete student test.");
            }
        } catch (Exception e) {
            String errorMessage = "Failed to delete student test. Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @RequestMapping(path = "/student_tests/student/{studentId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAllStudentTestsByStudentId(@PathVariable("studentId") int studentId) {
        try {
            boolean success = studentTestDao.deleteAllStudentTestsByStudentId(studentId);

            if (success) {
                return ResponseEntity.ok("All student tests deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete all student tests.");
            }
        } catch (Exception e) {
            String errorMessage = "Failed to delete all student tests. Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @RequestMapping(path = "/student_tests/tests/{testId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAllStudentTestsByTestId(@PathVariable("testId") int testId) {
        try {
            boolean success = studentTestDao.deleteAllStudentTestsByTestId(testId);

            if (success) {
                return ResponseEntity.ok("All student tests deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete all student tests.");
            }
        } catch (Exception e) {
            String errorMessage = "Failed to delete all student tests. Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

}
