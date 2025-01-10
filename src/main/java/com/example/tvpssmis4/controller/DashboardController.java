package com.example.tvpssmis4.controller;

import com.example.tvpssmis4.model.SchoolInformation;
import com.example.tvpssmis4.service.SchoolInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private SchoolInformationService schoolInformationService;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<SchoolInformation> schools = schoolInformationService.getAllSchools();
        model.addAttribute("schools", schools);
        return "AdminAnalyticsViews/SchoolInfo"; // This will render dashboard.html
    }

    @GetMapping("/report")
    public String showReport() {
        return "AdminAnalyticsViews/Report"; // Renders report.html
    }

    @GetMapping("/analysis")
    public String showAnalysis() {
        return "AdminAnalyticsViews/Analysis"; // Renders analysis.html
    }

    @GetMapping("/SchoolDetails")
    public String showView() {
        return "AdminAnalyticsViews/SchoolDetails"; // Renders analysis.html
    }

    @GetMapping("/school-details/{id}")
    public String showSchoolDetails(@PathVariable Long id, Model model) {
        SchoolInformation school = schoolInformationService.getSchoolById(id); // Fetch school info by ID
        if (school == null) {
            return "error/404"; // Return an error page if the school is not found
        }
        model.addAttribute("school", school);
        return "AdminAnalyticsViews/SchoolDetails"; // Ensure this matches the actual template location
    }
}
