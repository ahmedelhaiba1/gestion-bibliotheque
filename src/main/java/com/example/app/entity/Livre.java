package com.example.app.entity;

import jakarta.persistence.*;

@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String auteur;
    private String isbn;
    private boolean disponible = true;

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
}
