package fr.eni.projet.enchere.bo;

import java.time.LocalDate;

public class Enchere {
    private LocalDate dateEnchere;
    private Integer montantEnchere;
    private Utilisateur utilisateur;
    private Article article;
    public Enchere() {
    }

    public Enchere(LocalDate dateEnchere, Integer montantEnchere, Utilisateur utilisateur, Article article) {
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.utilisateur = utilisateur;
        this.article = article;
    }

    public LocalDate getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(LocalDate dateEnchere) {
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
