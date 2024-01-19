package fr.eni.projet.enchere.dal.impl;

import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Enchere;
import fr.eni.projet.enchere.bo.Utilisateur;
import fr.eni.projet.enchere.dal.EnchereDAO;
import fr.eni.projet.enchere.dal.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile("sql")
public class EnchereDAOSQL implements EnchereDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    UtilisateurDAO utilisateurDAO;

    public EnchereDAOSQL(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void add(Article article) {
        String sql = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) " +
                "VALUES (:utilisateur, :noArticle, :dateDebutEncheres, :miseAPrix);";
        Utilisateur utilisateur = this.utilisateurDAO.find(article.getUtilisateur().getNoUtilisateur());
        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("utilisateur", utilisateur.getNoUtilisateur());

    }

    @Override
    public Enchere find(Article article) {
        String sql = "SELECT * FROM ARTICLES_VENDUS" +
                " INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur " +
                "INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie WHERE no_article = :noArticle;";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("noArticle", article.getNoArticle());

        Enchere enchere = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new BeanPropertyRowMapper<>(Enchere.class));
        return enchere;
    }
}
