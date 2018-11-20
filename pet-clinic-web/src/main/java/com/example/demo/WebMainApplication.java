package com.example.demo;

import com.example.demo.model.FakeDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebMainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(WebMainApplication.class, args);

        FakeDatabase fakeDatabase = (FakeDatabase) ctx.getBean(FakeDatabase.class);

        System.out.println("fakeDatabase.getUser(): " + fakeDatabase.getUser());
        System.out.println("fakeDatabase.getPassword(): " + fakeDatabase.getPassword());
        System.out.println("fakeDatabase.getDatabase(): " + fakeDatabase.getDatabase());
    }
}
