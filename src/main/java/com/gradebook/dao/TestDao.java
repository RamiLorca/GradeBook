package com.gradebook.dao;

import com.gradebook.model.Test;
import java.util.List;

public interface TestDao {

    public List<Test> getTests();

    public boolean addTest(String testName, String testSubject, int numberOfQuestions);

    public boolean deleteTest(int testId);

}
