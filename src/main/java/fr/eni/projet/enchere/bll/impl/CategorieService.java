package fr.eni.projet.enchere.bll.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet.enchere.bll.IntCategorieService;
import fr.eni.projet.enchere.bo.Categorie;
import fr.eni.projet.enchere.dal.CategorieDAO;

@Service
public class CategorieService implements IntCategorieService {

    // Lien vers l'interface de la DAL
    private CategorieDAO categorieDAO;


    // Constructeur IoC
    public CategorieService(CategorieDAO categorieDAO) {
        super();
        this.categorieDAO = categorieDAO;
    }

    // METHODES CRUD
    @Override
    public List<Categorie> findAll() {
        return categorieDAO.findAll();
    }

    @Override
    public Categorie findById(Integer id) {
        return categorieDAO.find(id);
    }

}
