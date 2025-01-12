package com.example.tvpssmis4.controller;

import com.example.tvpssmis4.model.SchoolInformation;
import com.example.tvpssmis4.model.User;
import com.example.tvpssmis4.model.Video;
import com.example.tvpssmis4.repository.UserRepository;
import com.example.tvpssmis4.repository.VideoRepository;
import com.example.tvpssmis4.service.VideoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/gpm")
public class GPMController {

    @Autowired
    UserRepository userRepository;



    @Autowired
    private VideoRepository videoRepository;

    private final VideoService videoService;

    public GPMController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        String email = (String) session.getAttribute("email");
        User user = userRepository.findByUsernameOrEmail(username, email);
        // Check if user is logged in and has correct role
        if (session.getAttribute("role") == null ||
                !session.getAttribute("role").equals("GPM")) {
            return "redirect:/login";
        }


        model.addAttribute("user", user);
        return "/GPM/dashboard";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        // Check if user is logged in and has correct role
        if (session.getAttribute("role") == null ||
                !session.getAttribute("role").equals("GPM")) {
            return "redirect:/login";
        }

        model.addAttribute("username", session.getAttribute("username"));
        return "/GPM/home";
    }

    @GetMapping("/content")
    public String content(Model model, HttpSession session) {
            List<Video> videos = videoService.getAllVideos();
        model.addAttribute(]]"videos", videos);
            return "/GPM/ManageContent";

    }

    @GetMapping("/edit_content")
    public String editContent(Model model, HttpSession session) {

        return "/GPM/EditContent";

    }

    @PostMapping("/upload_content")
    public String uploadContent(@ModelAttribute Video video, Model model, HttpSession session) {
        videoService.addVideo(video);
        return "/GPM/ManageContent";
    }

    @GetMapping("/uploadContent")
    public String showUploadContentPage(Model model) {
        return "/GPM/UploadContent";
    }


}
