package com.example.store.web;

import com.example.store.service.ManufacturerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }
    @GetMapping("/add-manufacturer-form")
    public String addManufacturerPage(Model model) {
        model.addAttribute("bodyContent", "add-manufacturer");
        return "add-manufacturer.html";
    }
    @PostMapping("/addManufacturer")
    public String saveManufacturer(
                              @RequestParam String name,
                              @RequestParam String description,
                              @RequestParam String email) {
        this.manufacturerService.create(name,description,email);
        return "redirect:/home";
    }
    @GetMapping("/manufacturers")
    public String listManufacturers(Model model) {
        model.addAttribute("manufacturer", manufacturerService.listAll());
        model.addAttribute("bodyContent", "manufacturerlist");
        return "manufacturerlist.html";
    }
    @PostMapping("/deleteManufacturer")
    public String deleteManufacturer(@RequestParam Long id) {
        this.manufacturerService.delete(id);
        return "redirect:/home";
    }

}