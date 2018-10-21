package com.example.demo.controllers;

import com.example.demo.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.demo.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

class OwnerControllerTest {
    @Mock
    OwnerService ownerService;

    @Mock
    Model model;

    @Autowired
    MockMvc mvc = null;

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

    @Test
    void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"));
    }
}
