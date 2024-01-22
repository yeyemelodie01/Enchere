package fr.eni.projet.enchere.dal.impl;

import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Categorie;
import fr.eni.projet.enchere.bo.Utilisateur;
import fr.eni.projet.enchere.dal.ArticleDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
    public Integer create(Article article) {
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
    public Article find(Integer idArticle) {
        String sql = "SELECT * FROM ARTICLES_VENDUS" +
                " INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur " +
                "INNER JOIN CATEGORIES ON ARTICLES_VENDUS.no_categorie = CATEGORIES.no_categorie WHERE no_article = :noArticle;";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("noArticle", idArticle);

        return this.namedParameterJdbcTemplate.queryForObject(
                sql,
                namedParameters,
                (resultSet, rowNum) -> {
                    Article article = new Article();
                    article.setNoArticle(resultSet.getInt("no_article"));
                    article.setNomArticle(resultSet.getString("nom_article"));
                    article.setDescription(resultSet.getString("description"));
                    article.setDateDebutEncheres(LocalDate.parse(resultSet.getString("date_debut_encheres")));
                    article.setDateFinEncheres(LocalDate.parse(resultSet.getString("date_fin_encheres")));
                    article.setMiseAPrix(resultSet.getInt("prix_initial"));
                    article.setPrixVente(resultSet.getInt("prix_vente"));


                    // Charger l'utilisateur
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setNoUtilisateur(resultSet.getInt("no_utilisateur"));
                    article.setUtilisateur(utilisateur);

                    // Charger la catégorie
                    Categorie categorie = new Categorie();
                    categorie.setNoCategorie(resultSet.getInt("no_categorie"));
                    article.setCategorieArticle(categorie);

                    return article;
                }
        );
    }

    @Override
    public List<Article> findAll() {
        String sql = "SELECT * FROM ARTICLES_VENDUS";
        List<Article> articleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Article.class));
        return articleList;
    }

    @Override
    public void update(Article article) {
        String sql = "UPDATE ARTICLES_VENDUS SET nom_article = :nomArticle, description = :description, date_debut_encheres = :dateDebutEncheres, date_fin_encheres = :dateFinEncheres, prix_initial = :miseAPrix, prix_vente = :prixVente, no_utilisateur = :utilisateur, no_categorie = :categorieArticle WHERE no_article = :noArticle;";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("nomArticle", article.getNomArticle())
                .addValue("description", article.getDescription())
                .addValue("dateDebutEncheres", article.getDateDebutEncheres())
                .addValue("dateFinEncheres", article.getDateFinEncheres())
                .addValue("miseAPrix", article.getMiseAPrix())
                .addValue("prixVente", article.getPrixVente())
                .addValue("utilisateur", article.getUtilisateur().getNoUtilisateur())
                .addValue("categorieArticle", article.getCategorieArticle().getNoCategorie())
                .addValue("noArticle", article.getNoArticle());

        namedParameterJdbcTemplate.update(sql,namedParameters);
    }

    @Override
    public void delete(Integer noArticle) {
        String sql = "DELETE FROM ARTICLES_VENDUS WHERE no_article = :no_article";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_article", noArticle);
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }
}
