package fr.eni.projet.enchere.dal;

import fr.eni.projet.enchere.bo.Article;

import java.util.List;

public interface ArticleDAO {
    int create(Article article);

    Article read(Integer noArticle);

    List<Article> findAll();

    void update(Integer Utilisateur, Article article);

    void delete(Integer noArticle);


}
