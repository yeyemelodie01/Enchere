package fr.eni.projet.enchere.bo;

import java.time.LocalDate;

public class Article {
    private Integer noArticle;
    private String nomArticle;
    private String description;
    private LocalDate dateDebutEncheres;
    private LocalDate dateFinEncheres;
    private Integer miseAPrix;
    private Integer prixVente;
    private String etatVente;

    // Relations unidirectionnelles
    private Utilisateur utilisateur;
    private Categorie categorieArticle;
    private Retrait lieuRetrait;
    private Enchere enchere;


    public Article() {
    }

    public Article(Integer noArticle, String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, Integer miseAPrix, Integer prixVente, String etatVente, Utilisateur utilisateur, Categorie categorieArticle, Retrait lieuRetrait) {
        this.noArticle = noArticle;
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
        this.utilisateur = utilisateur;
        this.categorieArticle = categorieArticle;
        this.lieuRetrait = lieuRetrait;
    }

    public Article(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, Integer miseAPrix, Integer prixVente, Utilisateur utilisateur, Categorie categorieArticle) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.utilisateur = utilisateur;
        this.categorieArticle = categorieArticle;
    }

    public Article(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, Integer miseAPrix, Integer prixVente, String etatVente, Utilisateur utilisateur, Categorie categorieArticle, Retrait lieuRetrait, Enchere enchere) {
        this.nomArticle = nomArticle;
        this.description = description;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
        this.utilisateur = utilisateur;
        this.categorieArticle = categorieArticle;
        this.lieuRetrait = lieuRetrait;
        this.enchere = enchere;
    }

    public Integer getNoArticle() {
        return noArticle;
    }

    public void setNoArticle(Integer noArticle) {
        this.noArticle = noArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public LocalDate getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(LocalDate dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public Integer getMiseAPrix() {
        return miseAPrix;
    }

    public void setMiseAPrix(Integer miseAPrix) {
        this.miseAPrix = miseAPrix;
    }

    public Integer getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(Integer prixVente) {
        this.prixVente = prixVente;
    }

    public String getEtatVente() {
        return etatVente;
    }

    public void setEtatVente(String etatVente) {
        this.etatVente = etatVente;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Categorie getCategorieArticle() {
        return categorieArticle;
    }

    public void setCategorieArticle(Categorie categorieArticle) {
        this.categorieArticle = categorieArticle;
    }

    public Retrait getLieuRetrait() {
        return lieuRetrait;
    }

    public void setLieuRetrait(Retrait lieuRetrait) {
        this.lieuRetrait = lieuRetrait;
    }

    public Enchere getEnchere() {
        return enchere;
    }

    public void setEnchere(Enchere enchere) {
        this.enchere = enchere;
    }

    @Override
    public String toString() {
        return "Article{" +
                "noArticle=" + noArticle +
                ", nomArticle='" + nomArticle + '\'' +
                ", description='" + description + '\'' +
                ", dateDebutEncheres=" + dateDebutEncheres +
                ", dateFinEncheres=" + dateFinEncheres +
                ", miseAPrix=" + miseAPrix +
                ", prixVente=" + prixVente +
                ", etatVente='" + etatVente + '\'' +
                ", utilisateur=" + utilisateur +
                ", categorieArticle=" + categorieArticle +
                ", lieuRetrait=" + lieuRetrait +
                ", enchere=" + enchere +
                '}';
    }
}
