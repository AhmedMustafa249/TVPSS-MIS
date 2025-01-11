package com.example.tvpssmis4.controller;

import com.example.tvpssmis4.model.Report;
import com.example.tvpssmis4.model.SchoolInformation;
import com.example.tvpssmis4.service.ReportService;
import com.example.tvpssmis4.service.SchoolInformationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;



@Controller
@RequestMapping("/jpnj")
public class JPNJController {

    @Autowired
    private SchoolInformationService schoolInformationService;

    @Autowired
    private ReportService reportService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (session.getAttribute("role") == null ||
                !session.getAttribute("role").equals("JPNJ")) {
            return "redirect:/login";
        }

        model.addAttribute("username", session.getAttribute("username"));
        return "JPNJ/dashboard";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        if (session.getAttribute("role") == null ||
                !session.getAttribute("role").equals("JPNJ")) {
            return "redirect:/login";
        }

        model.addAttribute("username", session.getAttribute("username"));
        return "JPNJ/home";
    }

    @GetMapping("/dashboard/analysis")
    public String analysisDashboard(Model model) {
        // Fetch school data from the database
        List<SchoolInformation> schools = schoolInformationService.getAllSchools();

        // Calculate total schools and version counts
        long totalSchools = schools.size();
        long version4Count = schools.stream().filter(s -> s.getVersion() == 4).count();
        long version3Count = schools.stream().filter(s -> s.getVersion() == 3).count();
        long version2Count = schools.stream().filter(s -> s.getVersion() == 2).count();
        long version1Count = schools.stream().filter(s -> s.getVersion() == 1).count();
        long version0Count = schools.stream().filter(s -> s.getVersion() == 0).count();

        // Add data to the model
        model.addAttribute("totalSchools", totalSchools);
        model.addAttribute("version4Count", version4Count);
        model.addAttribute("version3Count", version3Count);
        model.addAttribute("version2Count", version2Count);
        model.addAttribute("version1Count", version1Count);
        model.addAttribute("version0Count", version0Count);

        return "JPNJ/analysis";
    }

    @GetMapping("/dashboard/report")
    public String getReports(Model model, HttpSession session) {
        // Ensure the user has the JPNJ role
        if (session.getAttribute("role") == null || !session.getAttribute("role").equals("JPNJ")) {
            return "redirect:/login";
        }

        // Fetch all reports
        List<Report> reports = reportService.getAllReports();
        model.addAttribute("reports", reports);

        return "JPNJ/report"; // Points to the report.html view for JPNJ
    }

    @PostMapping("/jpnj/dashboard/report/add")
    public String addReport(@RequestParam("reportName") String reportName, HttpSession session) {
        if (session.getAttribute("role") == null || !session.getAttribute("role").equals("JPNJ")) {
            return "redirect:/login";
        }

        // Add logic to save the report
        Report newReport = new Report();
        newReport.setName(reportName);
        newReport.setIssuedDate(LocalDate.now()); // Example: set current date
        reportService.addReport(newReport);

        return "redirect:/jpnj/dashboard/report";
    }

    @PostMapping("/jpnj/dashboard/report/delete/{id}")
    public String deleteReport(@PathVariable Long id, HttpSession session) {
        // Ensure the user has the JPNJ role
        if (session.getAttribute("role") == null || !session.getAttribute("role").equals("JPNJ")) {
            return "redirect:/login";
        }

        // Delete the report
        reportService.deleteReport(id);
        return "redirect:/jpnj/dashboard/report";
    }
}
