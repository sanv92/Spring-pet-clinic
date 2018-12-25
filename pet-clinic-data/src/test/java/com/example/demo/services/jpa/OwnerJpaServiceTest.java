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
        owner1.setId(1L);
        Owner owner2 = new Owner();
        owner2.setId(2L);

        owners.add(owner1);
        owners.add(owner2);

        assertEquals(2, owners.size());
    }

    @Test
    void getOwners2() {
        List<Owner> owner = new ArrayList<>();

        Owner owner1 = new Owner();
        owner1.setId(1L);
        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner.add(owner1);
        owner.add(owner2);

        when(ownerRepository.findAll()).thenReturn(owner);
        Set<Owner> owners = ownerJpaService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }
}