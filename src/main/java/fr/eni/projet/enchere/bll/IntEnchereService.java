package fr.eni.projet.enchere.bll;

import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Enchere;

import java.util.List;

public interface IntEnchereService{
    void add(Article article);
    Enchere find(Article article);
    List<Enchere> findAll();
}
