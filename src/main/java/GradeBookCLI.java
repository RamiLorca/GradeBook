import application.GradeBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class GradeBookCLI {

    public static void main (String[] args) {

        GradeBook gradeBook = new GradeBook();
        gradeBook.runHomeScreen();
    }

}
