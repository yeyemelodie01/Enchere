package fr.eni.projet.enchere.dal;

import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Retrait;

public interface RetraitDAO {

	void addLieuRetrait(Article article);
	Retrait findLieuRetrait(Article noArticle);
}
