package com.example.app.controller;

import com.example.app.entity.Livre;
import com.example.app.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livres")
public class LivreController {

    @Autowired
    private LivreService livreService;

    @GetMapping
    public List<Livre> getAllLivres() {
        return livreService.getAllLivres();
    }

    @GetMapping("/{id}")
    public Livre getLivreById(@PathVariable Long id) {
        return livreService.getLivreById(id);
    }

    @PostMapping
    public Livre addLivre(@RequestBody Livre livre) {
        return livreService.saveLivre(livre);
    }

    @PutMapping("/{id}")
    public Livre updateLivre(@PathVariable Long id, @RequestBody Livre livre) {
        livre.setId(id);
        return livreService.saveLivre(livre);
    }

    @DeleteMapping("/{id}")
    public void deleteLivre(@PathVariable Long id) {
        livreService.deleteLivre(id);
    }
}
