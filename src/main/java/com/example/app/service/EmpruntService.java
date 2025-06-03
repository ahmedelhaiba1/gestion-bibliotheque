package com.example.bibliotheque.service;

import com.example.bibliotheque.model.Emprunt;
import com.example.bibliotheque.repository.EmpruntRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpruntService {

    private final EmpruntRepository empruntRepository;

    public EmpruntService(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }

    public void enregistrer(Emprunt emprunt) {
        empruntRepository.save(emprunt);
    }

    public List<Emprunt> listerTous() {
        return empruntRepository.findAll();
    }
}