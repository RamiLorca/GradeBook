package com.gradebook.dao;

import com.gradebook.model.Student;

import java.util.List;

public interface StudentDao {

    public List<Student> getStudents();

    public boolean addStudent(String firstName, String lastName);

    public boolean updateStudent(int studentId, String firstName, String lastName);

    public boolean deleteStudent(int studentId);

}
