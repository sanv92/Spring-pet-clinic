package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

public class Owner extends Person {
    private String address;
    private String city;
    private String phone;

    private Set<Pet> pets = new HashSet<>();

    public Owner() {}

    public Owner(String address, String city, String phone, Set<Pet> pets) {
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.pets = pets;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
