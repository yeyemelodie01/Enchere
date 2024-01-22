package fr.eni.projet.enchere.bll.impl;

import fr.eni.projet.enchere.bll.IntEnchereService;
import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Enchere;
import fr.eni.projet.enchere.dal.EnchereDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnchereService implements IntEnchereService {

    private EnchereDAO enchereDAO;

    public EnchereService(EnchereDAO enchereDAO) {
        this.enchereDAO = enchereDAO;
    }

    @Override
    public void add(Article article) {
        this.enchereDAO.add(article);
    }

    @Override
    public Enchere find(Article article) {
        return this.enchereDAO.find(article);
    }

    @Override
    public List<Enchere> findAll() {
        return this.enchereDAO.findAll();
    }
}
