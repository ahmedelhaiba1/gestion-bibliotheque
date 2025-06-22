package com.example.bibliotheque.controller;

import com.example.bibliotheque.model.Emprunt;
import com.example.bibliotheque.service.EmpruntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.time.LocalDate;

@Controller
public class EmpruntWebController {

    @Autowired
    private EmpruntService empruntService;

    @GetMapping("/emprunter")
    public String afficherFormulaire(Model model) {
        model.addAttribute("emprunt", new Emprunt());
        return "emprunts/form";
    }

    @PostMapping("/emprunter")
    public String enregistrerEmprunt(@RequestParam String bookTitle,
                                     @RequestParam String borrowerName,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate borrowDate,
                                     @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate) {

        Emprunt emprunt = new Emprunt();
        emprunt.setBookTitle(bookTitle);
        emprunt.setBorrowerName(borrowerName);
        emprunt.setBorrowDate(borrowDate);
        emprunt.setReturnDate(returnDate);

        empruntService.enregistrer(emprunt);
        return "redirect:/historique";
    }
    @GetMapping("/historique")
    public String afficherHistorique(Model model) {
        model.addAttribute("emprunts", empruntService.listerEmprunts());
        return "emprunts/historique";
    }
}