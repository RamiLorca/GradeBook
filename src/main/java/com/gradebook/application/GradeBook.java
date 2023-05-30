//package com.gradebook.application;
//
//import com.gradebook.ui.UserInput;
//import com.gradebook.ui.UserOutput;
//
//public class GradeBook {
//
//    public void runHomeScreen () {
//
//        while(true) {
//
//            UserOutput.displayHomeScreen();
//            String choice = UserInput.getHomeScreenOption();
//
//            if(choice.equals("students")) {
//                UserOutput.displayStudentsScreen();
//                runStudentScreen();
//            } else if (choice.equals("tests")) {
//                UserOutput.displayTestsScreen();
//                runTestScreen();
//            } else if (choice.equals("grades")) {
//                UserOutput.displayGradesScreen();
//                runGradesScreen();
//            } else if (choice.equals("quit")) {
//                UserOutput.displayGoodbyeScreen();
//                exitProgram();
//            }
//        }
//    }
//
//    public void runStudentScreen () {
//
//        while(true) {
//
//            UserOutput.displayStudentsScreen();
//            String choice = UserInput.getStudentScreenOption();
//
//            if(choice.equals("create")) {
//                UserOutput.displayCreateStudentScreen();
//            } else if (choice.equals("delete")) {
//                UserOutput.displayDeleteStudentScreen();
//            } else if (choice.equals("pull")) {
//                UserOutput.displayPullStudentRecordScreen();
//            } else if (choice.equals("back")) {
//                UserOutput.displayHomeScreen();
//                runHomeScreen();
//            }
//        }
//    }
//
//    public void runTestScreen () {
//
//        while(true) {
//
//            UserOutput.displayTestsScreen();
//            String choice = UserInput.getTestsScreenOption();
//
//            if(choice.equals("create")) {
//                UserOutput.displayCreateNewTestScreen();
//            } else if (choice.equals("delete")) {
//                UserOutput.displayDeleteTestScreen();
//            } else if (choice.equals("pull")) {
//                UserOutput.displayPullTestRecordScreen();
//            } else if (choice.equals("back")) {
//                UserOutput.displayHomeScreen();
//                runHomeScreen();
//            }
//        }
//    }
//
//    public void runGradesScreen () {
//
//        while(true) {
//
//            UserOutput.displayGradesScreen();
//            String choice = UserInput.getGradesScreenOption();
//
//            if(choice.equals("assign/edit")) {
//                UserOutput.displayAssignEditTestGradeScreen();
//            } else if (choice.equals("delete")) {
//                UserOutput.displayDeleteTestGradeScreen();
//            } else if (choice.equals("back")) {
//                UserOutput.displayHomeScreen();
//                runHomeScreen();
//            }
//        }
//    }
//
//    public void exitProgram () {
//        System.exit(0);
//    }
//
//}
