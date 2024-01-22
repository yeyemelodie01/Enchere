package fr.eni.projet.enchere.bll;

import java.util.List;

import fr.eni.projet.enchere.bo.Categorie;

public interface IntCategorieService {
	
	List<Categorie> findAll();
	
	Categorie findById(Integer id);

}
