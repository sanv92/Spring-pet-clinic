package com.example.demo.controllers;

import com.example.demo.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/index",  "/index.html"})
    public String index(Model model) {
        System.out.println("ownerService.findAll()): " + ownerService.findAll());
        model.addAttribute("owners", ownerService.findAll());

        return "owners/index";
    }
}
