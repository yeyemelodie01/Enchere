package fr.eni.projet.enchere.dal.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import fr.eni.projet.enchere.bo.Utilisateur;
import fr.eni.projet.enchere.dal.UtilisateurDAO;

@Repository
public class UtilisateurDAOSQL implements UtilisateurDAO {
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public UtilisateurDAOSQL(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public void create(Utilisateur utilisateur) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// Récupérer le mot de passe non crypté
		String motDePasse = utilisateur.getMotDePasse();
		System.out.println("mot de passe avant: " + motDePasse);
		// Crypter le mot de passe avec BCryptPasswordEncoder
		String motDePasseCrypte = passwordEncoder.encode(motDePasse);
		System.out.println("mot de passe après: " + motDePasseCrypte);
		// Mettre à jour l'objet Utilisateur avec le mot de passe crypté
		utilisateur.setMotDePasse("{bcrypt}" + motDePasseCrypte);

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		String sql = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, role)"
				+ " VALUES (:pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :motDePasse, 200, 'UTILISATEUR');";
		
		BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(utilisateur);

		namedParameterJdbcTemplate.update(sql, namedParameters, keyHolder);
		
		if (keyHolder.getKey() != null)
			utilisateur.setNoUtilisateur(keyHolder.getKey().intValue());
	}

	@Override
	public Utilisateur find(Integer id) {
		String sql = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = :noUtilisateur";
		System.out.println("vous etes dans find: " + id);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("noUtilisateur", id);
		
		Utilisateur utilisateur = null;
		try {
			utilisateur = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, 
					new BeanPropertyRowMapper<>(Utilisateur.class));
		} catch (EmptyResultDataAccessException e) {
			utilisateur = null;
		}
		
		return utilisateur;
	}

	@Override
	public void update(Utilisateur utilisateur) {
		String sql = "UPDATE UTILISATEURS SET pseudo = :pseudo, nom = :nom, prenom = :prenom, email = :email, telephone = :telephone, rue = :rue, code_postal = :codePostal, ville = :ville, mot_de_passe = :motDePasse";

		BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(utilisateur);

		namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public List<Utilisateur> findAll() {
		String sql = "SELECT pseudo, nom, prenom, email, rue, code_postal, ville, FROM UTILISATEURS;";
		List<Utilisateur> utilisateurs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Utilisateur.class));
		
		return utilisateurs;
	}

	@Override
	public void supprimerUtilisateur(Integer noUtilisateur) {
		String sql = "DELETE FROM UTILISATEURS WHERE no_utilisateur = :id";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", noUtilisateur);
		
		namedParameterJdbcTemplate.update(sql, namedParameters);
	}

}
