package com.example.tvpssmis4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentManagementController {
    @GetMapping("/contenti")
    public String content(Model model) {
        model.addAttribute("title", "Test content page");
        return "ContentManagement/ManageContent";
    }


        @GetMapping("/edit_content")
        public String edit_content(Model model) {
            model.addAttribute("title", "Test content page");
            return "ContentManagement/EditContent";
        }
}
