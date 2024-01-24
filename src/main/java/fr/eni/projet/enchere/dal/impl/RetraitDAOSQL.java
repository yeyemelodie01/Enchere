package fr.eni.projet.enchere.dal.impl;

import fr.eni.projet.enchere.bo.Utilisateur;
import fr.eni.projet.enchere.dal.UtilisateurDAO;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Retrait;
import fr.eni.projet.enchere.dal.RetraitDAO;

@Repository
@Profile("sql")
public class RetraitDAOSQL implements RetraitDAO {
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private UtilisateurDAO utilisateurDAO;

	public RetraitDAOSQL(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	@Override
	public void addLieuRetrait(Article article) {
		String sql = "INSERT INTO RETRAITS (no_Article, rue, code_postal, ville) VALUES (:noArticle, :rue, :codePostal, :ville);";

		Utilisateur utilisateur = this.utilisateurDAO.findById(article.getUtilisateur().getNoUtilisateur());
		System.out.println("findUser: " + utilisateur);
		MapSqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("noArticle", article.getNoArticle())
				.addValue("rue", utilisateur.getRue())
				.addValue("codePostal", utilisateur.getCodePostal())
				.addValue("ville", utilisateur.getVille());

		namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public Retrait findLieuRetrait(Article noArticle) {
		String sql = "SELECT no_article, rue, code_postal, ville FROM RETRAITS WHERE no_article = :id;";
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", noArticle.getNoArticle());
				
		return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new BeanPropertyRowMapper<>(Retrait.class));
	}
}
