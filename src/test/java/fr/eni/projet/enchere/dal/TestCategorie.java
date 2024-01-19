package fr.eni.projet.enchere.dal;

import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Categorie;
import fr.eni.projet.enchere.bo.Utilisateur;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TestCategorie {

    @Autowired
    UtilisateurDAO utilisateurDAO;

    @Autowired
    CategorieDAO categorieDAO;

    @Autowired
    ArticleDAO articleDAO;


    /* Test UTILISATEURDAO */
    /*@Test
    @Transactional
    void Test01() {
        String sql = "SELECT [libelle] FROM [CATEGORIES] WHERE no_categorie = 1";
        String findCat = jdbcTemplate.queryForObject(sql, String.class);
        assertEquals("Electronique", findCat);
    }*/

    /*@Test
    @Transactional
    void test02_QueryForList() {
        String sql = "SELECT * FROM CATEGORIES;";
        List<Categorie> categories = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Categorie.class));
        assertNotNull(categories);
        assertEquals(4, categories.size());
        logger.info("QueryForList");
        categories.forEach(logger::info);
    }*/

    /*@Test
    void test02_CreateUtilisateur() {
        Utilisateur utilisateur = new Utilisateur("tutu","Sarkozy","Nicolas","nicolas@gmail.com", "0123456789", "1 rue de la Pointe", "35131", "Chartres", "autre", 30, "UTILISATEUR");
        this.utilisateurDAO.create(utilisateur);
        assertNotNull(utilisateur.getTelephone());
    }*/

    /*@Test
    @Transactional
    void findTest() {
        utilisateurDAO.find("mel@gmail.com");
        System.out.println(utilisateurDAO.find("mel@gmail.com").getNoUtilisateur());
    }*/

    /*@Test
    @Transactional
    void updateTest() {
        Utilisateur u = new Utilisateur("Ginko", "Sakata", "Gintoki", "yorozuya@edo.jp", "555777", "Unerue", "95200", "Edo", "SilverSoul", 500, "UTILISATEUR");
        utilisateurDAO.update(u);
    }*/

    /* TEST ARTICLEDAO */

    @Test
    void test02_CreateArticle() {
        Utilisateur utilisateur = utilisateurDAO.find("mel@gmail.com");
        Categorie categorie = this.categorieDAO.find(1);
        System.out.println(utilisateur);
        LocalDate dateNow = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(7);
       Article article = new Article("Television","c'est une grand télé", dateNow, dateEnd,210, 340,utilisateur,categorie);
        this.articleDAO.create(article);
        assertNotNull(article.getCategorieArticle());
    }
}
