package fr.eni.projet.enchere.bll;

import java.util.List;

import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Enchere;
import fr.eni.projet.enchere.dal.ArticleDAO;

public interface IntArticleService {
	
	List<Article> findAll();
	
	Article findById(Integer noArticle);

	void update(Article article);

	void delete(Integer noArticle);

}
