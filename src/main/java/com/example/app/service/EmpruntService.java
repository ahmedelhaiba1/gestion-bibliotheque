package com.example.app.service;

import com.example.app.model.Emprunt;
import com.example.app.repository.EmpruntRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpruntService {

    private final EmpruntRepository empruntRepository;

    public EmpruntService(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }

    public void enregistrer(Emprunt emprunt) {
        empruntRepository.save(emprunt);
    }

    public List<Emprunt> findAll() {
        return empruntRepository.findAll();
    }
    public List<Emprunt> findByStatus(String status) {
        return empruntRepository.findByStatus(status);
    }

    public Optional<Emprunt> findById(Long id) {
        return empruntRepository.findById(id);
    }

}