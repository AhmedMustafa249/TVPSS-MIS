package com.example.tvpssmis4.controller;

import com.example.tvpssmis4.model.SchoolInformation;
import com.example.tvpssmis4.model.User;
import com.example.tvpssmis4.repository.UserRepository;
import com.example.tvpssmis4.service.SchoolInformationService;
import com.example.tvpssmis4.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRepository userRepository;

    private final SchoolInformationService service;


    public SchoolInformationController(SchoolInformationService service) {
        this.service = service;
    }


    @GetMapping("/enter")
    public String showEnterSchoolInformationPage(Model model) {
        model.addAttribute("Message", "School Information");
        return "/GPM/enter_school_information"; // Name of the HTML file in templates directory
    }

    @GetMapping
    public List<SchoolInformation> getAllSchools() {
        return service.getAllSchools();
    }

    @PostMapping("/save_new_school")
    public String saveNewSchoolInformation(@ModelAttribute SchoolInformation school, HttpSession session) {
        String email = (String) session.getAttribute("email");
        String username = (String) session.getAttribute("username");

        User user = userRepository.findByUsernameOrEmail(username, email);
        if (user == null) {
            // Handle case where user is not found
            throw new RuntimeException("User not found");
        }

        school.updateVersion();
        user.setSchoolInformation(school);
        service.createOrUpdateSchool(school);
        return "redirect:/gpm/dashboard";
    }

    @PostMapping("/save")
    public String saveSchoolInformation(@ModelAttribute SchoolInformation school, Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        String username = (String) session.getAttribute("username");
        User user = userRepository.findByUsernameOrEmail(username, email);
        SchoolInformation existingSchool = user.getSchoolInformation();
        school.setId(existingSchool.getId());
        service.updateSchool(school);
        user.setSchoolInformation(school);
        return "redirect:/schools/display";
    }


    @GetMapping("/display")
    public String showDisplaySingleSchoolInformationPage(@ModelAttribute SchoolInformation school, Model model, HttpSession session) {
        // Fetch the school by its ID
        String email = (String) session.getAttribute("email");
        String username = (String) session.getAttribute("username");
        User user = userRepository.findByUsernameOrEmail(username, email);

        // Add the school to the model
        model.addAttribute("user", user);

        // Return the Thymeleaf template
        return "redirect:/gpm/dashboard"; // Name of the HTML file in templates directory
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

    @GetMapping("/pendingSchool")
    public String showPendingVerificationPage(@ModelAttribute SchoolInformation school) {
        if (!school.isVerified()) {
            return "redirect:/gpm/dashboard";
        }else{
            return "/home";
        }
    }

    @GetMapping("/school-details/{id}")
    public String showSchoolDetails(@PathVariable Long id, Model model) {
        SchoolInformation school = service.getSchoolById(id); // Fetch school info by ID
        if (school == null) {
            // Handle case when school is not found
            return "error/404"; // Use a 404 error page or redirect
        }
        model.addAttribute("school", school);
        return "AdminAnalyticsViews/SchoolDetails"; // Ensure this matches the file location
    }

    @GetMapping("/edit")
    public String showEditSchoolPage(@ModelAttribute SchoolInformation school,Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        String username = (String) session.getAttribute("username");
        User user = userRepository.findByUsernameOrEmail(username, email);
        model.addAttribute("user", user);
        return "GPM/edit_school_information";
    }

//    @GetMapping("/edit")
//    public String showEditSchoolPage(@PathVariable Long id) {
//        User user = userRepository.findByUsernameOrEmail(username, email);
//        model.addAttribute("user", user);
//        return "SchoolInformationViews/EditSchoolInformationPage";
//    }
}