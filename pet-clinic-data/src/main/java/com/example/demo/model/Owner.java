package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "owners")
public class Owner extends Person {
    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @JsonBackReference
    private Set<Pet> pets = new HashSet<>();

    public Owner() {
    }

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

    public Pet getPet(String name) {
        name = name.toLowerCase();

        for (Pet pet : pets) {
            if (!pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return pet;
                }
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                // ", pets=" + pets.stream().map(pet -> String.format("Pet {name:%s, petType:%s, owner:%s, birthDate:%s, visits:%s}", pet.getName(), pet.getPetType(), pet.getOwner(), pet.getBirthDate(), pet.getVisits())).collect(Collectors.toSet()) +
            '}';
    }
}
