package main.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld first_bean =
                (HelloWorld) applicationContext.getBean("helloworld");
        HelloWorld second_bean =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(first_bean == second_bean);

        Cat first_cat = (Cat) applicationContext.getBean("cat");
        Cat second_cat = (Cat) applicationContext.getBean("cat");

        System.out.println(first_cat == second_cat);


    }
}