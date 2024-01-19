package fr.eni.projet.enchere.bo;

import java.util.*;

public class Categorie {
    private Integer noCategorie;
    private String libelle;
    private List<Article> articleList = new ArrayList<>();

    public Categorie() {
    }

    public Categorie(Integer noCategorie, String libelle, List<Article> articleVenduList) {
        this.noCategorie = noCategorie;
        this.libelle = libelle;
        this.articleList = articleVenduList;
    }

    public Categorie(String libelle) {
        this.libelle = libelle;
    }

    public Integer getNoCategorie() {
        return noCategorie;
    }

    public void setNoCategorie(Integer noCategorie) {
        this.noCategorie = noCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Article> getArticleVenduList() {
        return articleList;
    }

    public void setArticleVenduList(List<Article> articleVenduList) {
        this.articleList = articleVenduList;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "no_categorie=" + noCategorie +
                ", libelle='" + libelle + '\'' +
                ", articleVenduList=" + articleList +
                '}';
    }
}
