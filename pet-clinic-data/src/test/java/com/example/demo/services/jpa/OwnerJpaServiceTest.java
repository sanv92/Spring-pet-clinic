package com.example.demo.services.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.model.Owner;
import com.example.demo.repositories.OwnerRepository;
import com.example.demo.repositories.PetRepository;
import com.example.demo.repositories.PetTypeRepository;


class OwnerJpaServiceTest {
    OwnerJpaService ownerJpaService;

    @Mock
    OwnerRepository ownerRepository;
    // OwnerRepository ownerRepository = Mockito.mock(OwnerRepository.class);

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    // Owner returnOwner;

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        ownerJpaService = new OwnerJpaService(ownerRepository, petRepository, petTypeRepository);
        // returnOwner = Owner.builder().id(1l).lastName(LAST_NAME).build();
    }

    @Test
    void getOwners() {
        Set<Owner> owners = ownerJpaService.findAll();

        assertEquals(0, owners.size());
    }

    @Test
    void getOwnersCount() {
        Set<Owner> owners = ownerJpaService.findAll();
        Owner owner1 = new Owner();
        Owner owner2 = new Owner();

        owners.add(owner1);
        owners.add(owner2);

        assertEquals(2, owners.size());
    }

    @Test
    void getOwners2() {
        List<Owner> ownerData = new ArrayList<>();

        ownerData.add(new Owner());
        ownerData.add(new Owner());

        when(ownerRepository.findAll()).thenReturn(ownerData);
        Set<Owner> owners = ownerJpaService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }
}