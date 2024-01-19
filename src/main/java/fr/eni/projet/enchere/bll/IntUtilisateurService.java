package fr.eni.projet.enchere.bll;

import java.util.List;

import fr.eni.projet.enchere.bo.Utilisateur;

public interface IntUtilisateurService {
	
	List<Utilisateur> getUtilisateurs();
	
	Utilisateur findUtilisateur(Integer id);
	void create(Utilisateur utilisateur);
}
