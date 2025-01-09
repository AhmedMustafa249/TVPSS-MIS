package com.example.tvpssmis4.controller;

import com.example.tvpssmis4.model.SchoolInformation;
import com.example.tvpssmis4.service.SchoolInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolInformationController {

    private final SchoolInformationService service;

    public SchoolInformationController(SchoolInformationService service) {
        this.service = service;
    }


    @GetMapping("/enter")
    public String showEnterSchoolInformationPage() {
        return "EnterSchoolInformationPage"; // Name of the HTML file in templates directory
    }
    @GetMapping
    public List<SchoolInformation> getAllSchools() {
        return service.getAllSchools();
    }

    @PostMapping("/save")
    public String saveSchoolInformation(@ModelAttribute SchoolInformation school, Model model) {
        service.createOrUpdateSchool(school);
        return "redirect:/schools/display";
    }

    @GetMapping("/display")
    public String showDisplaySchoolInformationPage(Model model) {
        model.addAttribute("schools", service.getAllSchools());
        return "DisplaySchoolInformationPage"; // Name of the HTML file in templates directory
    }

    @GetMapping("/{id}")
    public SchoolInformation getSchoolById(@PathVariable Long id) {
        return service.getSchoolById(id);
    }

    @PostMapping
    public SchoolInformation createOrUpdateSchool(@RequestBody SchoolInformation school) {
        System.out.println("Received School: " + school);
        return service.createOrUpdateSchool(school);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        service.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pending")
    public List<SchoolInformation> getPendingVerificationSchools() {
        return service.getPendingVerificationSchools();
    }
}
