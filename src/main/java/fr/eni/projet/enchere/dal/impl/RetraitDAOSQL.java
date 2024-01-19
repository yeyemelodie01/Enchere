package fr.eni.projet.enchere.dal.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Retrait;
import fr.eni.projet.enchere.dal.RetraitDAO;

@Repository
public class RetraitDAOSQL implements RetraitDAO {	

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public RetraitDAOSQL(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public Retrait findLieuRetrait(Article noArticle) {
		String sql = "SELECT no_article, rue, code_postal, ville FROM RETRAITS WHERE no_article = id";
		
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", noArticle);
				
		return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new BeanPropertyRowMapper<>(Retrait.class));
	}
}
