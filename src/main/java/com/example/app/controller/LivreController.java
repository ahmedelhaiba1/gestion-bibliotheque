package com.example.app.controller;

import com.example.app.entity.Livre;
import com.example.app.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/livres")
public class LivreController {

    @Autowired
    private LivreService livreService;

    @GetMapping
    public String showLivres(Model model) {
        List<Livre> livres = livreService.getAllLivres();
        model.addAttribute("livres", livres);
        return "gestion_livres";
    }

    @PostMapping
    public String addLivre(
            @RequestParam("titre") String titre,
            @RequestParam("auteur") String auteur,
            @RequestParam("datePublication") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePublication,
            @RequestParam("image") MultipartFile imageFile
    ) throws IOException {
        livreService.saveLivre(titre, auteur, datePublication, imageFile);
        return "redirect:/livres";
    }
    @PostMapping("/update/{id}")
    public String updateLivre(
            @PathVariable Long id,
            @RequestParam("titre") String titre,
            @RequestParam("auteur") String auteur,
            @RequestParam("datePublication") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePublication
    ) {
        livreService.updateLivre(id, titre, auteur, datePublication);
        return "redirect:/livres";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteLivre(@PathVariable Long id) {
        livreService.deleteLivre(id);
        return "redirect:/livres";
    }
}
