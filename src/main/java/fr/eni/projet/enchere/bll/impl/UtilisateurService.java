package fr.eni.projet.enchere.bll.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.projet.enchere.bll.IntUtilisateurService;
import fr.eni.projet.enchere.bo.Utilisateur;
import fr.eni.projet.enchere.dal.UtilisateurDAO;

@Service
public class UtilisateurService implements IntUtilisateurService {

	private UtilisateurDAO utilisateurDAO;

	public UtilisateurService(UtilisateurDAO utilisateurDAO) {
		super();
		this.utilisateurDAO = utilisateurDAO;
	}

	@Override
	public List<Utilisateur> getUtilisateurs() {
		return this.utilisateurDAO.findAll();
	}

	@Override
	public Utilisateur findUtilisateur(String email) {
		return this.utilisateurDAO.find(email);
	}

	@Override
	public void create(Utilisateur utilisateur) {
		utilisateurDAO.create(utilisateur);
	}

}
