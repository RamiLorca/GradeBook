package com.gradebook.dao;

import com.gradebook.model.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcStudentDao implements StudentDao {

    private JdbcTemplate template;
    public JdbcStudentDao(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Student> getStudents() {
        String getStudentsSql = "SELECT student_id, first_name, last_name FROM student";

        List<Student> allStudents = new ArrayList<>();

        SqlRowSet result = template.queryForRowSet(getStudentsSql);

        while(result.next()){
            int studentId = result.getInt("student_id");
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");

            Student student = new Student(studentId, firstName, lastName);

            allStudents.add(student);
        }
        return allStudents;
    }

    @Override
    public boolean addStudent(String firstName, String lastName) {
        String addStudentSql = "INSERT INTO student (first_name, last_name) VALUES (?, ?) RETURNING student_id";

        try {
            if(firstName == null || lastName == null) {
                return false;
            }

            Integer result = template.queryForObject(addStudentSql, Integer.class, firstName, lastName);

        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateStudent(int studentId, String firstName, String lastName) {
        try {
            String updateStudentSql = "UPDATE student SET first_name = ?, last_name = ? WHERE student_id = ?";
            int rowsAffected = template.update(updateStudentSql, firstName, lastName, studentId);
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            System.out.println("Error updating student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStudent(int studentId) {
        try{
            String deleteStudentSql = "DELETE FROM student WHERE student_id = ?";
            int rowsAffected = template.update(deleteStudentSql, studentId);
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            System.out.println("Error deleting student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
