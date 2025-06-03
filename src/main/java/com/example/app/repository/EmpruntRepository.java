package com.example.bibliotheque.repository;

import com.example.bibliotheque.model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    List<Emprunt> findByUtilisateurId(Long utilisateurId);
    List<Emprunt> findByLivreId(Long livreId);
}
