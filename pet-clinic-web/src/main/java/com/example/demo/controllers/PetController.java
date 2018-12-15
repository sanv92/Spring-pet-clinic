package com.example.demo.controllers;

import com.example.demo.model.Owner;
import com.example.demo.model.Pet;
import com.example.demo.model.PetType;
import com.example.demo.services.OwnerService;
import com.example.demo.services.PetService;
import com.example.demo.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
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

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Model model) {
        Pet pet = new Pet();
        model.addAttribute("pet", pet);

        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult bindingResult, Model model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName()) != null){
            bindingResult.rejectValue("name", "duplicate", "already exists");
        }
        pet.setOwner(owner);

        if (bindingResult.hasErrors()) {
            model.addAttribute("pet", pet);

            return "pets/createOrUpdatePetForm";
        }
        else {
            petService.save(pet);

            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable("petId") Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));

        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@PathVariable("ownerId") Long ownerId, @PathVariable("petId") Long petId, Owner owner, @Valid Pet pet, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);

            return "owners/createOrUpdatePetForm";
        }
        else {
            pet.setId(petId);
            pet.setOwner(owner);
            Pet savedPet = petService.save(pet);

            return "redirect:/owners/" + ownerId + "/pets/" + savedPet.getId() + "/edit";
        }
    }
}
