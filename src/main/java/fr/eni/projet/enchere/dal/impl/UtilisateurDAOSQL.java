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
import org.springframework.stereotype.Repository;

import fr.eni.projet.enchere.bo.Utilisateur;
import fr.eni.projet.enchere.dal.UtilisateurDAO;

@Repository
@Profile("sql")
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
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		String sql = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, role)"
				+ " VALUES (:pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :motDePasse, 200, 'UTILISATEUR');";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("pseudo", utilisateur.getPseudo())
						.addValue("nom", utilisateur.getNom())
						.addValue("prenom", utilisateur.getPrenom())
						.addValue("email", utilisateur.getEmail())
						.addValue("telephone", utilisateur.getTelephone())
						.addValue("rue", utilisateur.getRue())
						.addValue("codePostal", utilisateur.getCodePostal())
						.addValue("ville", utilisateur.getVille())
						.addValue("motDePasse", utilisateur.getMotDePasse());

		namedParameterJdbcTemplate.update(sql, namedParameters, keyHolder);

		if (keyHolder.getKey() != null)
			utilisateur.setNoUtilisateur(keyHolder.getKey().intValue());
	}

	@Override
	public Utilisateur find(Integer id) {
		String sql = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = :noUtilisateur";

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
	public Utilisateur findByPseudo(String pseudo) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("pseudo", pseudo);

		Utilisateur utilisateur = namedParameterJdbcTemplate.queryForObject("SELECT * FROM UTILISATEURS WHERE pseudo = :pseudo",namedParameters, new BeanPropertyRowMapper<>(Utilisateur.class));

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
		String sql = "SELECT no_utilisateur,pseudo, nom, prenom, email, rue, code_postal, ville, credit, role FROM UTILISATEURS;";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Utilisateur.class));
	}

	@Override
	public void delete(Integer noUtilisateur) {
		String sql = "DELETE FROM UTILISATEURS WHERE no_utilisateur = :id";

		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", noUtilisateur);

		namedParameterJdbcTemplate.update(sql, namedParameters);
	}

}
