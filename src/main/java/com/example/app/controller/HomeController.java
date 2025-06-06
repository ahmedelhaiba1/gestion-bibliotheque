package com.example.app.controller;
import com.example.app.entity.Livre;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.example.app.service.LivreService;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private LivreService livreService;
    @GetMapping("/")
    public String home(HttpSession session) {
        return "hello";
    }
    @GetMapping("/dashboard")
    public String showLivres(Model model) {
        List<Livre> livres = livreService.getAllLivres();
        model.addAttribute("livres", livres);
        return "dashboard";
    }

    @GetMapping("/notifications")
    public String notifications(HttpSession session) {
        return "notifications"; // maps to gestion_emprunts.html
    }
    @GetMapping("/profile")
    public String profile(HttpSession session) {
        return "profile"; // maps to gestion_emprunts.html
    }

    @GetMapping("/historique")
    public String historique(HttpSession session) {
        return "historique"; // maps to gestion_emprunts.html
    }
}
