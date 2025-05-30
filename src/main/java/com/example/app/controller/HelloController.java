package com.example.app.controller;

import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String indexPage() {
        return "hello"; 
    }
    @GetMapping("/signup")
    public String SignUpPage() {
        return "signup"; 
    }
    @GetMapping("/signin")
    public String SignInPage() {
        return "signin"; 
    }
    @GetMapping("/dashboard")
    public String DashboardPage() {
        return "dashboard"; 
    }
    @GetMapping("/emprunter")
    public String BorrowPage() {
        return "emprunter"; 
    }
    @GetMapping("/notifications")
    public String NotificationPage() {
        return "notifications"; 
    }
    @GetMapping("/profile")
    public String ProfilePage() {
        return "profile"; 
    }
    @GetMapping("/historique")
    public String HistoriquePage() {
        return "historique"; 
    }
    @GetMapping("/gestion_emprunts")
    public String EmpruntsPage() {
        return "gestion_emprunts"; 
    }
    @GetMapping("/gestion_livres")
    public String LivresPage() {
        return "gestion_livres"; 
    }
}
