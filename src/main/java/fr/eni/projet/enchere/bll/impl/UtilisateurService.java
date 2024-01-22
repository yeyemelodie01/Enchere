package fr.eni.projet.enchere.bll.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet.enchere.bll.IntUtilisateurService;
import fr.eni.projet.enchere.bo.Utilisateur;
import fr.eni.projet.enchere.dal.UtilisateurDAO;

@Service
public class UtilisateurService implements IntUtilisateurService {

	// Lien vers l'interface de la DAL
	private UtilisateurDAO utilisateurDAO;


	// Constructeur IoC
	public UtilisateurService(UtilisateurDAO utilisateurDAO) {
		super();
		this.utilisateurDAO = utilisateurDAO;
	}

	// METHODES CRUD
	@Override
	public List<Utilisateur> findAll() {
		return this.utilisateurDAO.findAll();
	}

	@Override
	public Utilisateur findById(Integer id) {
		return this.utilisateurDAO.findById(id);
	}

	@Override
	public void create(Utilisateur utilisateur) {
		utilisateurDAO.create(utilisateur);
	}

	public void update(Utilisateur utilisateur) {
		utilisateurDAO.update(utilisateur);
	}

	public void delete(Integer noUtilisateur) { utilisateurDAO.delete(noUtilisateur);

	}
}
