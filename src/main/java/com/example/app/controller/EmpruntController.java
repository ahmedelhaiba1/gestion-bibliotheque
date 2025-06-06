package com.example.app.controller;

import com.example.app.entity.Livre;
import com.example.app.model.Emprunt;
import com.example.app.model.*;

import com.example.app.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class EmpruntController {

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private LivreService livreService;

    @GetMapping("/emprunter")
    public String afficherFormulaire(@RequestParam(required = false) Long bookId,
                                     @RequestParam(required = false) String bookTitle,
                                     Model model,
                                     HttpSession session) {

        Emprunt emprunt = new Emprunt();
        emprunt.setBookTitle(bookTitle);

        if (bookId != null) {
            model.addAttribute("bookId", bookId);
        }

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            emprunt.setBorrowerName(loggedInUser.getUsername());
        }

        model.addAttribute("emprunt", emprunt);
        return "emprunter";
    }





    @PostMapping("/emprunter")
    public String enregistrerEmprunt(@RequestParam Long bookId,
                                     @RequestParam String bookTitle,
                                     @RequestParam String borrowerName,
                                     Model model,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate borrowDate,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {

        Livre livre = livreService.findById(bookId);
        model.addAttribute("livre", livre);

        Emprunt emprunt = new Emprunt();
        emprunt.setLivre(livre);
        emprunt.setBookTitle(bookTitle);
        emprunt.setBorrowerName(borrowerName);
        emprunt.setBorrowDate(borrowDate);
        emprunt.setReturnDate(returnDate);
        emprunt.setStatus("EN_ATTENTE");

        empruntService.enregistrer(emprunt);
        return "redirect:/historique";
    }

    @GetMapping("/gestion_emprunts")
    public String afficherDemandesEmprunts(Model model) {
        List<Emprunt> demandes = empruntService.findByStatus("EN_ATTENTE");
        List<Emprunt> historique = empruntService.findAll(); // or a filtered list
        model.addAttribute("demandes", demandes);
        model.addAttribute("historique", historique);
        return "gestion_emprunts";
    }
    @PostMapping("/traiter_emprunt")
    public String traiterEmprunt(@RequestParam Long empruntId,
                                 @RequestParam String action) {
        Optional<Emprunt> optionalEmprunt = empruntService.findById(empruntId);

        if (optionalEmprunt.isPresent()) {
            Emprunt emprunt = optionalEmprunt.get();
            if ("accepter".equals(action)) {
                emprunt.setStatus("ACCEPTÉ");
            } else if ("refuser".equals(action)) {
                emprunt.setStatus("REFUSÉ");
            }
            empruntService.enregistrer(emprunt);
        }

        return "redirect:/gestion_emprunts";
    }


}