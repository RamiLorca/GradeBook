package com.gradebook.controller;

import com.gradebook.dao.StudentDao;
import com.gradebook.dao.StudentTestDao;
import com.gradebook.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class StudentController {
    @Autowired
    StudentDao studentDao;

    @Autowired
    StudentTestDao studentTestDao;

    @RequestMapping(path = "/students", method= RequestMethod.GET)
    public List<Student> getStudents() {
        return studentDao.getStudents();
    }

    @RequestMapping(path = "/students/add", method = RequestMethod.POST)
    public void addStudent(@RequestBody Student student) {
        String firstName = student.getFirstName();
        String lastName = student.getLastName();

        if(!studentDao.addStudent(firstName, lastName)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to add student.");
        }
    }

    @RequestMapping(path = "/students/update/{studentId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateStudent(@PathVariable("studentId") int studentId, @RequestBody Map<String, String> requestBody) {
        try {
            String firstName = requestBody.get("firstName");
            String lastName = requestBody.get("lastName");

            if(firstName != null && lastName != null) {
                boolean success = studentDao.updateStudent(studentId, firstName, lastName);

                if (success) {
                    return ResponseEntity.ok("Student updated successfully.");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update student.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid value provided.");
            }
        } catch (Exception e) {
            String errorMessage = "Failed to update student test. Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @RequestMapping(path = "/students/{studentId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") int studentId) {
        try{
            studentTestDao.deleteAllStudentTestsByStudentId(studentId);

            boolean success = studentDao.deleteStudent(studentId);

            if(success) {
                return ResponseEntity.ok("Student deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete student.");
            }
        } catch (Exception e) {
            String errorMessage = "Failed to delete student. Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

}
