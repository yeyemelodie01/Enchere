package fr.eni.projet.enchere.bll.impl;

import fr.eni.projet.enchere.bll.IntRetraitService;
import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Retrait;
import fr.eni.projet.enchere.dal.RetraitDAO;
import org.springframework.stereotype.Service;

@Service
public class RetraitService implements IntRetraitService {

    private RetraitDAO retraitDAO;

    public RetraitService(RetraitDAO retraitDAO) {
        this.retraitDAO = retraitDAO;
    }

    @Override
    public void add(Article article) {
        this.retraitDAO.addLieuRetrait(article);
    }

    @Override
    public Retrait find(Article noArticle) {
        return this.retraitDAO.findLieuRetrait(noArticle);
    }
}
