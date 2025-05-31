package com.example.app.controller;

import jakarta.servlet.http.HttpSession;
import com.example.app.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.app.service.UserService;

@Controller
@RequestMapping("/auth")
@SessionAttributes("loggedInUser") // Store loggedInUser in session
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Show login page (GET /auth/signin)
    @GetMapping("/signin")
    public String showLoginForm() {
        return "signin";  // Thymeleaf template signin.html
    }

    // Handle login form submit (POST /auth/login)
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        RedirectAttributes redirectAttributes) {

        if (userService.login(username, password)) {
            User loggedInUser = userService.findByUsername(username);
            model.addAttribute("loggedInUser", loggedInUser);
            return "redirect:/dashboard";  // Redirect to home page
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Nom d’utilisateur ou mot de passe incorrect");
            return "redirect:/auth/signin";  // Redirect back to login page
        }
    }

    // Show registration page (GET /auth/register)
    @GetMapping("/register")
    public String showRegisterForm() {
        return "signup";  // Thymeleaf template register.html
    }

    // Handle registration submit (POST /auth/register)
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           RedirectAttributes redirectAttributes) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        if (userService.register(user)) {
            redirectAttributes.addFlashAttribute("successMessage", "Inscription réussie. Vous pouvez vous connecter.");
            return "redirect:/auth/signin";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Nom d’utilisateur déjà pris.");
            return "redirect:/auth/register";
        }
    }

    // Optional: logout method
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Clear session
        return "redirect:/auth/signin";
    }

}
