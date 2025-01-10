package com.example.tvpssmis4.controller;

import com.example.tvpssmis4.repository.UserRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.tvpssmis4.model.User;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard(Authentication authentication) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        switch (user.getRole()) {
            case "GPM":
                return "redirect:/gpm/dashboard";
            case "PPD":
                return "redirect:/ppd/dashboard";
            case "JPNJ":
                return "redirect:/jpnj/dashboard";
            default:
                return "redirect:/default/dashboard"; // Fallback for unknown roles
        }// This will render dashboard.html
    }

    @GetMapping("/report")
    public String showReport() {
        return "AdminAnalyticsViews/Report"; // Renders report.html
    }

    @GetMapping("/analysis")
    public String showAnalysis() {
        return "AdminAnalyticsViews/Analysis"; // Renders analysis.html
    }

    @GetMapping("/view")
    public String showView() {
        return "AdminAnalyticsViews/View"; // Renders analysis.html
    }
}
