package fr.eni.projet.enchere.bll;

import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Retrait;

public interface IntRetraitService {
    void add(Article article);
    Retrait find(Article noArticle);
}
