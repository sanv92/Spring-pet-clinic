package com.example.demo.model;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class OwnerTest {
    private Owner owner;

    @BeforeEach
    void setup() {
        this.owner = new Owner();
    }

    @Test
    void getAddress() throws Exception {
        owner.setAddress("test - 1");

        assertEquals("test - 1", owner.getAddress());
    }

    @Test
    void getCity() throws Exception {
        owner.setCity("test - 2");

        assertEquals("test - 2", owner.getCity());
    }

    @Test
    void getPhone() throws Exception {
        owner.setPhone("test - 3");

        assertEquals("test - 3", owner.getPhone());
    }

    @Test
    void getPets() throws Exception {
        Set<Pet> pets = new HashSet<>();

        Pet pet1 = new Pet();
        pet1.setName("Test Pet name - 1");
        pets.add(pet1);

        owner.setPets(pets);

        Set<Pet> result = new HashSet<>();
        result.add(pet1);

        assertEquals(result, owner.getPets());
    }
}