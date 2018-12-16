package com.example.demo;

import com.example.demo.model.FakeDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebMainApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebMainApplication.class);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(WebMainApplication.class, args);

        FakeDatabase fakeDatabase = (FakeDatabase) ctx.getBean(FakeDatabase.class);

        System.out.println("fakeDatabase.getUser(): " + fakeDatabase.getUser());
        System.out.println("fakeDatabase.getPassword(): " + fakeDatabase.getPassword());
        System.out.println("fakeDatabase.getDatabase(): " + fakeDatabase.getDatabase());
    }
}
