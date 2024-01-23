package fr.eni.projet.enchere.bll;

import java.util.List;

import fr.eni.projet.enchere.bo.Utilisateur;

public interface IntUtilisateurService {
	
	List<Utilisateur> findAll();
	
    Utilisateur findById(Integer id);

    Utilisateur findByPseudo(String pseudo);

    void create(Utilisateur utilisateur);

    void update(Utilisateur utilisateur);

    void delete(Integer noUtilisateur);
}
