package com.gradebook.dao;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import com.gradebook.model.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTestDao implements TestDao {

    private JdbcTemplate template;
    public JdbcTestDao(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Test> getTests() {
        String getTestsSql = "SELECT test_id, test_name, test_subject, number_of_questions FROM test";

        List<Test> allTests = new ArrayList<>();

        SqlRowSet result = template.queryForRowSet(getTestsSql);

        while(result.next()) {
            int testId = result.getInt("test_id");
            String testName = result.getString("test_name");
            String testSubject = result.getString("test_subject");
            int numberOfQuestions = result.getInt("number_of_questions");

            Test test = new Test(testId, testName, testSubject, numberOfQuestions);

            allTests.add(test);
        }
        return allTests;
    }

    @Override
    public boolean addTest(String testName, String testSubject, int numberOfQuestions) {
        String addTestSql = "INSERT INTO test (test_name, test_subject, number_of_questions) VALUES (?, ?, ?) RETURNING test_id";

        try {
            if(testName == null || testSubject == null || numberOfQuestions < 1) {
                return false;
            }
            Integer result = template.queryForObject(addTestSql, Integer.class, testName, testSubject, numberOfQuestions);

        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteTest(int testId) {
        try{
            String deleteStudentSql = "DELETE FROM test WHERE test_id = ?";
            int rowsAffected = template.update(deleteStudentSql, testId);
            return rowsAffected > 0;
        } catch (DataAccessException e) {
            System.out.println("Error deleting test: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
