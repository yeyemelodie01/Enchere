package fr.eni.projet.enchere.dal;

import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Enchere;

import java.util.List;

public interface EnchereDAO {
    void add(Article article);
    Enchere find(Article article);
    List<Enchere> findAll();
}
