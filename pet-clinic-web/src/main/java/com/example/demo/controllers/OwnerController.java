package com.example.demo.controllers;

import com.example.demo.model.Owner;
import com.example.demo.services.OwnerService;
import org.apache.logging.log4j.LogManager;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"", "/index",  "/index.html"})
    public String index(Model model) {
        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }

    @GetMapping("/search")
    public String findOwners(Model model) {
        model.addAttribute("owner", new Owner());

        return "owners/findOwners";
    }

    @GetMapping("/search/result")
    public String processFindForm(Owner owner, BindingResult bindingResult, Model model) {
        if (owner.getLastName() == null) {
            owner.setLastName("");
        }

        List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());
        if (results.isEmpty()) {
            bindingResult.rejectValue("lastName", "not.found", "not found");

            return "owners/findOwners";
        }
        else if (results.size() == 1) {
            owner = results.get(0);

            return "redirect:/owners/" + owner.getId();
        }
        else {
            model.addAttribute("owners", results);

            return "owners/findOwners";
        }
    }

    @GetMapping({"/{ownerId}"})
    public String showOwner(@PathVariable("ownerId") Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));

        return "owners/ownerDetails";
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", new Owner());

        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        }
        else {
            Owner savedOwner = ownerService.save(owner);

            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model) {
        Owner owner = ownerService.findById(ownerId);
        model.addAttribute("owner", owner);

        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid @ModelAttribute("owner") Owner owner, @PathVariable("ownerId") Long ownerId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        }
        else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);

            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}
