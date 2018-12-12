package com.example.demo.controllers;

import com.example.demo.model.Owner;
import com.example.demo.model.Pet;
import com.example.demo.model.PetType;
import com.example.demo.services.OwnerService;
import com.example.demo.services.PetService;
import com.example.demo.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;


@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Set<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Model model) {
        Pet pet = new Pet();
        model.addAttribute("pet", pet);

        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult bindingResult, Model model) {
        owner.getPets().add(pet);

        if (bindingResult.hasErrors()) {
            model.addAttribute("pet", pet);

            return "pets/createOrUpdatePetForm";
        }
        else {
            // ownerService.save(owner);
            // petService.save(pet);

            System.out.println("owner.getId(): " + owner.getId());

            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm() {
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm() {
        return "pets/createOrUpdatePetForm";
    }
}
