package com.example.demo.config;

import com.example.demo.model.FakeDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:datasource.properties")
public class DatabaseConfig {
    @Value("${data.username}")
    private String user;

    @Value("${data.password}")
    private String password;

    @Value("${data.database}")
    private String database;

    @Autowired
    Environment environment;

    @Bean
    public FakeDatabase fakeDataSource() {
        FakeDatabase db = new FakeDatabase();
        db.setUser(user);
        db.setPassword(password);
        db.setDatabase(database);

        String subject = environment.getProperty("data.username");
        System.out.println("Environment: " + subject);

        return db;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
