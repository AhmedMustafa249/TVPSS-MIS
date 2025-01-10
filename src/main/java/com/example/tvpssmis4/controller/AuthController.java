package com.example.tvpssmis4.controller;

import com.example.tvpssmis4.dto.LoginRequest;
import com.example.tvpssmis4.dto.RegisterRequest;
import com.example.tvpssmis4.model.User;
import com.example.tvpssmis4.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.tvpssmis4.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository; // Ensure this is injected // Inject UserRepository

    @Autowired
    private HttpSession session; // Add this

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "UserAuthenticationViews/login";
    }

    /*
    @PostMapping("/login")
    public String handleLogin(@Valid LoginRequest loginRequest, Model model) {
        boolean success = userService.login(loginRequest);
        if (success) {
            return "redirect:/dashboard";
        }
        model.addAttribute("error", "Invalid username or password");
        return "UserAuthenticationViews/login";
    }
    */

    @PostMapping("/login")
    public String handleLogin(@Valid LoginRequest loginRequest, Model model) {
        User user = userService.login(loginRequest);
        if (user != null) {
            // Store user info in session
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());

            // Redirect based on role
            return switch (user.getRole()) {
                case "GPM" -> "redirect:/gpm/dashboard";
                case "PPD" -> "redirect:/ppd/dashboard";
                case "JPNJ" -> "redirect:/jpnj/dashboard";
                default -> "redirect:/dashboard";
            };
        }
        model.addAttribute("error", "Invalid username or password");
        return "UserAuthenticationViews/login";
    }



    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "UserAuthenticationViews/register";
    }

    @PostMapping("/register")
    public String handleRegister(@Valid RegisterRequest registerRequest, Model model) {
        if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match");
            return "UserAuthenticationViews/register";
        }

        userService.register(registerRequest);
        return "redirect:/login";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordPage() {
        return "UserAuthenticationViews/resetPassword"; // Renders resetPassword.html
    }

    // Handle reset password form submission
    @PostMapping("/reset-password")
    public String handleResetPassword(@RequestParam("email") String email, Model model) {
        boolean success = userService.sendResetPasswordLink(email);
        if (success) {
            model.addAttribute("message", "A reset password link has been sent to your email.");
        } else {
            model.addAttribute("error", "No account found with that email.");
        }
        return "UserAuthenticationViews/resetPassword"; // Return to the same page with a success or error message
    }






    // ### TESTING CASES ###

    @GetMapping("/test-save")
    public String testSave() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPhoneNumber("1234567890");
        user.setPassword("testpassword");
        userRepository.save(user);

        System.out.println("UserRepository is null: " + (userRepository == null));

        return "/login";
    }

    @GetMapping("/test-register")
    public String testRegister() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("testuser");
        registerRequest.setEmail("test@example.com");
        registerRequest.setPhoneNumber("1234567890");
        registerRequest.setPassword("password123");
        registerRequest.setConfirmPassword("password123");

        userService.register(registerRequest);

        return "/login";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
