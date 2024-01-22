package fr.eni.projet.enchere.dal;

import fr.eni.projet.enchere.bo.Article;

import java.util.List;

public interface ArticleDAO {
    Integer create(Article article);

    Article find(Integer noArticle);

    List<Article> findAll();

    void update(Article article);

    void delete(Integer noArticle);

}