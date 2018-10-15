package com.example.demo.model;

import java.io.Serializable;

public class FakeDatabase implements Serializable {
    private String user;
    private String password;
    private String database;

    public FakeDatabase() {}

    public FakeDatabase(String user, String password, String database) {
        this.user = user;
        this.password = password;
        this.database = database;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
