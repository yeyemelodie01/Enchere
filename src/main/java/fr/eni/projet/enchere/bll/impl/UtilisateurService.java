package fr.eni.projet.enchere.bll.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.projet.enchere.bll.IntUtilisateurService;
import fr.eni.projet.enchere.bo.Utilisateur;
import fr.eni.projet.enchere.dal.UtilisateurDAO;

@Service
public class UtilisateurService implements IntUtilisateurService {

	// Lien vers l'interface de la DAL
	private UtilisateurDAO utilisateurDAO;

	private PasswordEncoder passwordEncoder;


	// Constructeur IoC
	public UtilisateurService(UtilisateurDAO utilisateurDAO, PasswordEncoder passwordEncoder) {
		super();
		this.utilisateurDAO = utilisateurDAO;
		this.passwordEncoder = passwordEncoder;
	}

	// METHODES CRUD
	@Override
	public List<Utilisateur> findAll() {
		return this.utilisateurDAO.findAll();
	}

	@Override
	public Utilisateur findById(Integer id) {
		return this.utilisateurDAO.find(id);
	}

	@Override
	public Utilisateur findByPseudo(String pseudo) {
		return this.utilisateurDAO.findByPseudo(pseudo);
	}

	@Override
	public void create(Utilisateur utilisateur) {
		utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse())); //

		this.utilisateurDAO.create(utilisateur);
	}

	@Override
	public void update(Utilisateur utilisateur) {
		utilisateurDAO.update(utilisateur);
	}

	@Override
	public void delete(Integer noUtilisateur) { utilisateurDAO.delete(noUtilisateur);

	}
}
