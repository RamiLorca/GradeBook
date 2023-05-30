//package com.gradebook.ui;
//
//import java.util.Scanner;
//import com.gradebook.application.*;
//
//public class UserInput {
//
//    private static Scanner scanner = new Scanner(System.in);
//
//    public static String getHomeScreenOption() {
//
//        String selectedOption = scanner.nextLine();
//        String option = selectedOption.trim();
//
//        if (option.equals("1")) {
//            return "students";
//        } else if (option.equals("2")) {
//            return "tests";
//        } else if (option.equals("3")) {
//            return "grades";
//        } else if (option.equals("4")) {
//            return "quit";
//        } else {
//            return "";
//        }
//    }
//
//    public static String getStudentScreenOption() {
//
//        String selectedOption = scanner.nextLine();
//        String option = selectedOption.trim();
//
//        if (option.equals("1")) {
//            return "create";
//        } else if (option.equals("2")) {
//            return "delete";
//        } else if (option.equals("3")) {
//            return "pull";
//        } else if (option.equals("4")) {
//            return "back";
//        } else {
//            return "";
//        }
//    }
//
//    public static String getTestsScreenOption() {
//
//        String selectedOption = scanner.nextLine();
//        String option = selectedOption.trim();
//
//        if (option.equals("1")) {
//            return "create";
//        } else if (option.equals("2")) {
//            return "delete";
//        } else if (option.equals("3")) {
//            return "pull";
//        } else if (option.equals("4")) {
//            return "back";
//        } else {
//            return "";
//        }
//    }
//
//    public static String getGradesScreenOption() {
//
//        String selectedOption = scanner.nextLine();
//        String option = selectedOption.trim();
//
//        if (option.equals("1")) {
//            return "assign/edit";
//        } else if (option.equals("2")) {
//            return "delete";
//        } else if (option.equals("3")) {
//            return "back";
//        } else {
//            return "";
//        }
//    }
//
//}
