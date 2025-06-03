package com.example.app.service;

import com.example.app.entity.Livre;
import java.util.List;

public interface LivreService {
    List<Livre> getAllLivres();
    Livre getLivreById(Long id);
    Livre saveLivre(Livre livre);
    void deleteLivre(Long id);
}
