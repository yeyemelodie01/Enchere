package fr.eni.projet.enchere.dal.impl;

import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.dal.ArticleDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Profile("sql")
public class ArticleDAOSQL implements ArticleDAO {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /*CONSTRUCTOR*/

    public ArticleDAOSQL(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    /*GETTERS & SETTERS */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int create(Article article) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) "
                + "VALUES (:nomArticle, :description, :dateDebutEncheres, :dateFinEncheres, :miseAPrix, :prixVente, :utilisateur, :categorieArticle)";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("nomArticle", article.getNomArticle())
                .addValue("description", article.getDescription())
                .addValue("dateDebutEncheres", article.getDateDebutEncheres())
                .addValue("dateFinEncheres", article.getDateFinEncheres())
                .addValue("miseAPrix", article.getMiseAPrix())
                .addValue("prixVente", article.getPrixVente())
                .addValue("utilisateur", article.getUtilisateur().getNoUtilisateur())
                .addValue("categorieArticle", article.getCategorieArticle().getNoCategorie());

            int counter = namedParameterJdbcTemplate.update(sql, namedParameters, keyHolder);

        if (keyHolder.getKey() != null) {
            article.setNoArticle(keyHolder.getKey().intValue());
        }
        return counter;
    }

    @Override
    public Article read(Integer noArticle) {
        String sql = "SELECT no_article FROM ARTICLES WHERE no_article= :no_article";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_article", noArticle);
        return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new BeanPropertyRowMapper<>(Article.class));
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM ARTICLES";
        List<Article> articleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class));
        return articleList;
    }

    @Override
    public void update(Integer Utilisateur, Article article) {
        String sql = "UPDATE ARTICLES SET nom_article = :nom_article, description = :description, date_debut_encheres = :date_debut_encheres, "
        + "date_fin_encheres = :date_fin_encheres, prix_initial = :prix_initial, prix_vente = :prix_vente WHERE no_article = :no_article";
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(article);

    }

    @Override
    public void delete(Integer noArticle) {
        String sql = "DELETE FROM ARTICLES WHERE no_article = :no_article";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_article", noArticle);
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }
}
