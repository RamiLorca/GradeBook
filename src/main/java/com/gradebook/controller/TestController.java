package com.gradebook.controller;

import com.gradebook.dao.StudentTestDao;
import com.gradebook.dao.TestDao;
import com.gradebook.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class TestController {
    @Autowired
    TestDao testDao;

    @Autowired
    StudentTestDao studentTestDao;

    @RequestMapping(path="/tests", method= RequestMethod.GET)
    public List<Test> getAllTests() {
        return testDao.getTests();
    }

    @RequestMapping(path = "/tests/add", method = RequestMethod.POST)
    public void addTest(@RequestBody Test test) {
        String testName = test.getTestName();
        String testSubject = test.getTestSubject();
        int numberOfQuestions = test.getNumberOfQuestions();

        if(!testDao.addTest(testName, testSubject, numberOfQuestions)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to add test.");
        }
    }

    @RequestMapping(path = "/tests/{testId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTest(@PathVariable("testId") int testId) {
        try{
            studentTestDao.deleteAllStudentTestsByTestId(testId);

            boolean success = testDao.deleteTest(testId);

            if(success) {
                return ResponseEntity.ok("Test deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete test.");
            }
        } catch (Exception e) {
            String errorMessage = "Failed to delete test. Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

}
