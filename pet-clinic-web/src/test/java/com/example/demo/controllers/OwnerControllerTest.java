package com.example.demo.controllers;

import com.example.demo.exceptions.NotFoundException;
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
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

class OwnerControllerTest {
    @Mock
    OwnerService ownerService;

    @Mock
    Model model;

    MockMvc mockMvc;

    @Autowired
    GlobalExceptionHandler globalExceptionHandler;

    @Autowired
    OwnerController controller;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.controller = new OwnerController(ownerService);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void index() {
        Set<Owner> owners = new HashSet<>();
        Owner owner1 = new Owner();
        owner1.setId(1L);

        Owner owner2 = new Owner();
        owner2.setId(2L);

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
    void showOwner() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        Owner owner = new Owner();
        owner.setId(1L);
        owner.setAddress("test address");
        owner.setCity("test city");
        owner.setPhone("444");

        when(ownerService.findById(1L)).thenReturn(owner);
        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))))
                .andExpect(model().attribute("owner", hasProperty("address", is("test address"))))
                .andExpect(model().attribute("owner", hasProperty("city", is("test city"))))
                .andExpect(model().attribute("owner", hasProperty("phone", is("444"))));
    }

    @Test
    void testMockMVC() throws Exception {
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"));
    }

    @Test
    void testOwnerNotFound() throws Exception {
        when(ownerService.findById(3000L)).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/owners/3000"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("errors/404"));
    }

    @Test
    void testOwnerNumberFormatException() throws Exception {
        when(ownerService.findById(3000L)).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/owners/qwerty"))
                .andExpect(status().isBadRequest())
                .andExpect(view().name("errors/400"));
    }
}
