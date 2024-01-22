package fr.eni.projet.enchere.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Enchere {
    private LocalDateTime dateEnchere;
    private Integer montantEnchere;
    private Utilisateur utilisateur;
    private Article article;
    public Enchere() {
    }

    public Enchere(Utilisateur utilisateur, Article article, LocalDateTime dateEnchere, Integer montantEnchere) {
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.utilisateur = utilisateur;
        this.article = article;
    }

    public LocalDateTime getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(LocalDateTime dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public Integer getMontantEnchere() {
        return montantEnchere;
    }

    public void setMontantEnchere(Integer montantEnchere) {
        this.montantEnchere = montantEnchere;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Enchere{" +
                "dateEnchere=" + dateEnchere +
                ", montantEnchere=" + montantEnchere +
                ", utilisateur=" + utilisateur +
                ", article=" + article +
                '}';
    }
}
