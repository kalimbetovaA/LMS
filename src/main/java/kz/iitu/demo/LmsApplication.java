package kz.iitu.demo;

import kz.iitu.demo.config.SpringConfigration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class LmsApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigration.class);


        LMS lms = context.getBean(LMS.class);
        lms.showMenu();
    }

}
