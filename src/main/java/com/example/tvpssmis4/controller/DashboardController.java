package com.example.tvpssmis4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard() {
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

    @GetMapping("/view")
    public String showView() {
        return "AdminAnalyticsViews/View"; // Renders analysis.html
    }
}
