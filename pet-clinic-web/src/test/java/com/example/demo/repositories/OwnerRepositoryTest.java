package com.example.demo.repositories;

import com.example.demo.model.Owner;
import com.example.demo.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class OwnerRepositoryTest {
    @Autowired
    private OwnerRepository ownerRepository;

    @BeforeEach
    void setUp() {}

    @Test
    void findAll() throws Exception {
        List<Owner> ownerOptionalStart = ownerRepository.findAll();
        assertEquals(0, ownerOptionalStart.size());

        Owner owner1 = new Owner();
        owner1.setFirstName("First Owner - Name 1");
        owner1.setLastName("Last Owner - Name 1");
        owner1.setAddress("111 test - Address");
        owner1.setCity("Tallinn");
        owner1.setPhone("+111");

        Pet pet1 = new Pet();
        pet1.setName("Test Pet name - 1");
        pet1.setOwner(owner1);
        pet1.setBirthDate(LocalDate.now());

        owner1.getPets().add(pet1);

        ownerRepository.save(owner1);

        List<Owner> ownerOptionalEnd = ownerRepository.findAll();
        assertEquals(1, ownerOptionalEnd.size());
    }
}
