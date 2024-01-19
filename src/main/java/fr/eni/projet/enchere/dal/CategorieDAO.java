package fr.eni.projet.enchere.dal;

import fr.eni.projet.enchere.bo.Categorie;

import java.util.List;

public interface CategorieDAO {
    Categorie find(Integer id);
    List<Categorie> findAll();
}
