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
@SessionAttributes("loggedInUser")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signin")
    public String showLoginForm() {
        return "signin";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model,
                        RedirectAttributes redirectAttributes) {

        if (userService.login(email, password)) {
            User loggedInUser = userService.findByEmail(email);
            model.addAttribute("loggedInUser", loggedInUser);

            return loggedInUser.isAdmin() ? "redirect:/gestion_emprunts" : "redirect:/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid email or password");
            return "redirect:/auth/signin";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "signup";
    }

    @PostMapping("/register")
    public String register(@RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String username,
                           Model model,
                           RedirectAttributes redirectAttributes) {

        if (userService.emailExists(email)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cet email est déjà utilisé.");
            return "redirect:/auth/register";
        }

        if (userService.usernameExists(username)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Ce nom d'utilisateur est déjà pris.");
            return "redirect:/auth/register";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setAdmin(email.contains("admin@"));
        user.setActive(true);

        userService.register(user);

        redirectAttributes.addFlashAttribute("successMessage", "Inscription réussie, veuillez vous connecter.");
        return "redirect:/auth/signin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/signin";
    }
    @GetMapping("/profile")
    public String showProfile(@SessionAttribute(value = "loggedInUser", required = false) User loggedInUser,
                              Model model,
                              RedirectAttributes redirectAttributes) {

        if (loggedInUser == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please log in first.");
            return "redirect:/auth/signin";
        }

        model.addAttribute("user", loggedInUser);
        return "profile";
    }




    @PostMapping("/profile/update")
    public String updateProfile(@SessionAttribute("loggedInUser") User loggedInUser,
                                @RequestParam String username,
                                @RequestParam String email,
                                RedirectAttributes redirectAttributes) {

        loggedInUser.setUsername(username);
        loggedInUser.setEmail(email);

        userService.updateUser(loggedInUser);

        redirectAttributes.addFlashAttribute("successMessage", "Profil mis à jour avec succès.");
        return "redirect:/auth/profile";
    }
    @PostMapping("/profile/delete")
    public String deleteProfile(@SessionAttribute("loggedInUser") User loggedInUser,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        userService.deleteUser(loggedInUser); // Implement this method in your UserService
        session.invalidate(); // Log out the user
        redirectAttributes.addFlashAttribute("successMessage", "Compte supprimé avec succès.");
        return "redirect:/auth/signin";
    }


}
