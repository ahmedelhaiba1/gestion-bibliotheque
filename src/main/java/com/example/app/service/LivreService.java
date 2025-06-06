package com.example.app.service;

import com.example.app.entity.Livre;
import com.example.app.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class LivreService {

    private static final String UPLOAD_DIR = "uploads";

    @Autowired
    private LivreRepository livreRepository;

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    public Livre getLivreById(Long id) {
        return livreRepository.findById(id).orElse(null);
    }

    public Livre saveLivre(String titre, String auteur, LocalDate datePublication, MultipartFile imageFile) throws IOException {
        String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        Livre livre = new Livre();
        livre.setTitre(titre);
        livre.setAuteur(auteur);
        livre.setDatePublication(datePublication);
        livre.setImageUrl(fileName);

        return livreRepository.save(livre);
    }
    public void updateLivre(Long id, String titre, String auteur, LocalDate datePublication) {
        Livre livre = livreRepository.findById(id).orElseThrow();
        livre.setTitre(titre);
        livre.setAuteur(auteur);
        livre.setDatePublication(datePublication);
        livreRepository.save(livre);
    }

    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }
    public Livre findById(Long id) {
        return livreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livre not found with id: " + id));
    }

}
