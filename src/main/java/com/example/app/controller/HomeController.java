package com.example.app.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(HttpSession session) {
        return "hello";
    }
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        return "dashboard";
    }
    @GetMapping("/gestion_livres")
    public String gestionLivres(HttpSession session) {
        return "gestion_livres"; // maps to gestion_livres.html
    }

    @GetMapping("/gestion_emprunts")
    public String gestionEmprunts(HttpSession session) {
        return "gestion_emprunts"; // maps to gestion_emprunts.html
    }
    @GetMapping("/notifications")
    public String notifications(HttpSession session) {
        return "notifications"; // maps to gestion_emprunts.html
    }
    @GetMapping("/profile")
    public String profile(HttpSession session) {
        return "profile"; // maps to gestion_emprunts.html
    }
    @GetMapping("/emprunter")
    public String emprunter(HttpSession session) {
        return "emprunter"; // maps to gestion_emprunts.html
    }
    @GetMapping("/historique")
    public String historique(HttpSession session) {
        return "historique"; // maps to gestion_emprunts.html
    }
}
