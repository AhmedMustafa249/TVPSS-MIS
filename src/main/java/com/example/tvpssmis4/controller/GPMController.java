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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
        model.addAttribute("videos", videos);
            return "/GPM/ManageContent";

    }

    @GetMapping("/edit_content/{id}")
    public String showEditContentPage(@PathVariable Long id, Model model, HttpSession session) {
        Video video = videoService.getVideoById(id);
        model.addAttribute("video", video);
        return "/GPM/EditContent";
    }

    @GetMapping("/delete_content/{id}")
    public String deleteContent(@ModelAttribute Video video) {
        videoService.deleteVideo(video);
        return "redirect:/gpm/content";
    }

    @PostMapping("/content/save/{id}")
    public String saveContent(@PathVariable Long id, @RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("visibility") String visibility, @RequestParam("thumbnailUrl") String thumbnailUrl, Model model, HttpSession session) {
        Video updatedVideo = new Video();
        updatedVideo.setId(id);
        updatedVideo.setTitle(title);
        updatedVideo.setDescription(description);
        updatedVideo.setThumbnailUrl(thumbnailUrl);
        updatedVideo.setVisibility(visibility);
        videoService.updateVideo(updatedVideo);
        return "redirect:/gpm/content";
    }

    @PostMapping("/upload_content")
    public String uploadContent(@ModelAttribute Video video, Model model, HttpSession session) {
            videoService.addVideo(video);
            return "redirect:/gpm/content";
    }

    @GetMapping("/uploadContent")
    public String showUploadContentPage(Model model) {
        return "/GPM/UploadContent";
    }


}
