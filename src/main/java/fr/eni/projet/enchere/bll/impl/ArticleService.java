package fr.eni.projet.enchere.bll.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet.enchere.bll.IntArticleService;
import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.dal.ArticleDAO;

@Service
public class ArticleService implements IntArticleService {

    // Lien vers l'interface de la DAL
    private ArticleDAO articleDAO;


    // Constructeur IoC
    public ArticleService(ArticleDAO articleDAO) {
        super();
        this.articleDAO = articleDAO;
    }

    // METHODES CRUD


    @Override
    public void create(Article article) {
        this.articleDAO.create(article);
    }

    @Override
    public List<Article> findAll() {
        return articleDAO.findAll();
    }

    @Override
    public Article findById(Integer noArticle) {
        return articleDAO.find(noArticle);
    }

    @Override
    public void update(Article article) {
        articleDAO.update(article);
    }

    @Override
    public void delete(Integer noArticle) {
        articleDAO.delete(noArticle);

    }


}
