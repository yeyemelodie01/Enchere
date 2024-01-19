package fr.eni.projet.enchere.dal;

import java.util.List;

import fr.eni.projet.enchere.bo.Utilisateur;

public interface UtilisateurDAO {
	
	void create(Utilisateur utilisateur);
	
	Utilisateur find(String email);
	
	void update(Utilisateur utilisateur);
	
	List<Utilisateur> findAll();
	
	void supprimerUtilisateur(Integer noUtilisateur);
	

}
