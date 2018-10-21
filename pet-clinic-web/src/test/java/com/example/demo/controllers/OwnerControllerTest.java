package com.example.demo.controllers;

import com.example.demo.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.services.OwnerService;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

class OwnerControllerTest {
    @Mock
    OwnerService ownerService;

    @Mock
    Model model;

    OwnerController controller;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.controller = new OwnerController(ownerService);
    }

    @Test
    void index() {
        Set<Owner> owners = new HashSet<>();
        Owner owner1 = new Owner();
        owner1.setId(1L);

        Owner owner2 = new Owner();
        owner2.setId(1L);

        owners.add(owner1);
        owners.add(owner2);

        when(ownerService.findAll()).thenReturn(owners);
        ArgumentCaptor<Set<Owner>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        String viewName = controller.index(model);

        assertEquals("owners/index", viewName);
        verify(ownerService, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("owners"), argumentCaptor.capture());

        Set<Owner> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }
}
