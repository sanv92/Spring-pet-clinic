package com.example.demo;

import com.example.demo.model.FakeDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);

        FakeDatabase fakeDatabase = (FakeDatabase) ctx.getBean(FakeDatabase.class);

        System.out.println(fakeDatabase.getUser());
        System.out.println(fakeDatabase.getPassword());
        System.out.println(fakeDatabase.getDatabase());
    }
}
