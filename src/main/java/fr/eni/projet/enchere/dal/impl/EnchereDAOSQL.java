package fr.eni.projet.enchere.dal.impl;

import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Categorie;
import fr.eni.projet.enchere.bo.Enchere;
import fr.eni.projet.enchere.bo.Utilisateur;
import fr.eni.projet.enchere.dal.ArticleDAO;
import fr.eni.projet.enchere.dal.EnchereDAO;
import fr.eni.projet.enchere.dal.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@Profile("sql")
public class EnchereDAOSQL implements EnchereDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    UtilisateurDAO utilisateurDAO;

    @Autowired
    ArticleDAO articleDAO;


    @Override
    public void add(Article article) {
        String sql = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) " +
                "VALUES (:utilisateur, :noArticle, :dateDebutEncheres, :miseAPrix);";
        Utilisateur utilisateur = this.utilisateurDAO.find(article.getUtilisateur().getNoUtilisateur());
        Article findArticle = this.articleDAO.find(article.getNoArticle());

        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("utilisateur", utilisateur.getNoUtilisateur())
                .addValue("noArticle", article.getNoArticle())
                .addValue("dateDebutEncheres", findArticle.getDateDebutEncheres())
                .addValue("miseAPrix", findArticle.getMiseAPrix());

        namedParameterJdbcTemplate.update(sql, namedParameters);

    }

    @Override
    public Enchere find(Article article) {
        String sql = "SELECT * FROM ENCHERES" +
                " INNER JOIN ARTICLES_VENDUS ON ENCHERES.no_article = ARTICLES_VENDUS.no_article " +
                " INNER JOIN UTILISATEURS ON ENCHERES.no_utilisateur = UTILISATEURS.no_utilisateur " +
                " WHERE ENCHERES.no_article = :noArticle;";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("noArticle", article.getNoArticle());
        System.out.println("on est dans l'enchère");
        System.out.println("article trouver: " + this.articleDAO.find(article.getNoArticle()));

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, (resultSet, rowNum) -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            Enchere enchere = new Enchere();
            enchere.setDateEnchere(LocalDateTime.parse(resultSet.getString("date_enchere"), dateTimeFormatter));
            enchere.setMontantEnchere(resultSet.getInt("montant_enchere"));

            /* Charger l'id de l'article */
            Article articleEnchere = new Article();
            articleEnchere.setNoArticle(resultSet.getInt("no_article"));
            enchere.setArticle(articleEnchere);

            /* Charger l'id de l'utilisateur */
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNoUtilisateur(resultSet.getInt("no_utilisateur"));
            enchere.setUtilisateur(utilisateur);

            return enchere;
        });
    }

    @Override
    public List<Enchere> findAll() {
        String sql = "SELECT * FROM ENCHERES";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Enchere.class));
    }
}
