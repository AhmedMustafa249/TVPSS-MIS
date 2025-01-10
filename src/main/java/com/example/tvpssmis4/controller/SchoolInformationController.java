package com.example.tvpssmis4.controller;

import com.example.tvpssmis4.model.SchoolInformation;
import com.example.tvpssmis4.service.SchoolInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequestMapping("/schools")
public class SchoolInformationController {

    private final SchoolInformationService service;

    public SchoolInformationController(SchoolInformationService service) {
        this.service = service;
    }


    @GetMapping("/enter")
    public String showEnterSchoolInformationPage(Model model) {
        model.addAttribute("Message", "School Information");
        return "SchoolInformationViews/EnterSchoolInformationPage"; // Name of the HTML file in templates directory
    }
    @GetMapping
    public List<SchoolInformation> getAllSchools() {
        return service.getAllSchools();
    }

    @PostMapping("/save")
    public String saveSchoolInformation(@ModelAttribute SchoolInformation school, Model model) {
        school.updateVersion();
        service.createOrUpdateSchool(school);
        return "redirect:/schools/display/"+school.getId();
    }


    @GetMapping("/display/{id}")
    public String showDisplaySingleSchoolInformationPage(@PathVariable Long id, Model model) {
        // Fetch the school by its ID
        SchoolInformation school = service.getSchoolById(id);

        // Add the school to the model
        model.addAttribute("school", school);

        // Return the Thymeleaf template
        return "SchoolInformationViews/DisplaySchoolInfoPage"; // Name of the HTML file in templates directory
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
