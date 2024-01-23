package fr.eni.projet.enchere.dal;

import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Categorie;
import fr.eni.projet.enchere.bo.Enchere;
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

    @Autowired
    RetraitDAO retraitDAO;

    @Autowired
    EnchereDAO enchereDAO;


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
        utilisateurDAO.findAll();
        System.out.println(utilisateurDAO.findAll());
    }*/

    /*@Test
    @Transactional
    void updateTest() {
        Utilisateur u = new Utilisateur("Ginko", "Sakata", "Gintoki", "yorozuya@edo.jp", "555777", "Unerue", "95200", "Edo", "SilverSoul", 500, "UTILISATEUR");
        utilisateurDAO.update(u);
    }*/

    /*@Test
    @Transactional
    void findByPseudoTest() {
        Utilisateur utilisateur = this.utilisateurDAO.findByPseudo("toto");
        this.utilisateurDAO.findByPseudo("toto");
        System.out.println("findUtilisaateurByPseudo: " + utilisateur);
    }*/


    /* TEST ARTICLEDAO */
    /*@Test
    @Transactional
    void test02_CreateArticle() {
        Utilisateur utilisateur = utilisateurDAO.find(1);
        Categorie categorie = this.categorieDAO.find(1);
        System.out.println(utilisateur);
        LocalDate dateNow = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(7);
       Article article = new Article("Television","c'est une grand télé", dateNow, dateEnd,210, 340,utilisateur,categorie);
        this.articleDAO.create(article);
        assertNotNull(article.getCategorieArticle());
    }*/
   /*  @Test
    @Transactional
    void findTest() {
        this.articleDAO.read(2);
        System.out.println("Trouver l'article avec son id: " + this.articleDAO.read(2));
    }

   @Test
    @Transactional
    void findAllTest() {
        this.articleDAO.findAll();
        System.out.println("Trouver tous les articles: " + this.articleDAO.findAll());
    }

    @Test
    @Transactional
    void updateTest() {
        Utilisateur utilisateur = utilisateurDAO.find("mel@gmail.com");
        Categorie categorie = this.categorieDAO.find(1);
        System.out.println(utilisateur);
        LocalDate dateNow = LocalDate.now();
        LocalDate dateEnd = LocalDate.now().plusDays(7);
        Article newArticle = new Article("Radio","Ancienne radio, en bon état ", dateNow, dateEnd,210, 340,utilisateur,categorie);
        this.articleDAO.update(newArticle);
        System.out.println("Modification de l'article: " + newArticle.getDescription());
    }

    @Test
    @Transactional
    void deleteTest() {
        this.articleDAO.delete(5);
    }*/


    /* TEST CATEGORIEDAO */
   /* @Test
    @Transactional
    void findTest() {
        this.categorieDAO.find(6);
        System.out.println("Trouver la catégorie avec son id: " + this.categorieDAO.find(6));
    }

    @Test
    @Transactional
    void findAllTest() {
        this.categorieDAO.findAll();
        System.out.println("Trouver toutes les catégories: " + this.categorieDAO.findAll());
    }*/


    /* TEST RETRAITDAO */
    /*@Test
    @Transactional
    void addTest() {
        Article article = this.articleDAO.find(1);
        System.out.println(article.getUtilisateur().getNoUtilisateur());
        this.retraitDAO.addLieuRetrait(article);
    }

    @Test
    @Transactional
    void findTest() {
        Article article = this.articleDAO.read(2);
        this.retraitDAO.findLieuRetrait(article);
        System.out.println("Trouver le lieu de retrait avec un article: " +  this.retraitDAO.findLieuRetrait(article));
    }*/

    /* TEST ENCHEREDAO */
    /*@Test
    void addTest() {
        Article article = this.articleDAO.find(1);
        System.out.println(article.getUtilisateur());
        System.out.println(article);
        this.enchereDAO.add(article);
    }

    @Test
    @Transactional
    void findTest() {
        Article article = this.articleDAO.find(2);
        this.enchereDAO.find(article);
        System.out.println("Trouver les encheres avec son article: " + this.enchereDAO.find(article));
    }

    @Test
    @Transactional
    void findAllTest() {
        this.enchereDAO.findAll();
        System.out.println("Trouver toutes les encheres: " + this.enchereDAO.findAll());
    }*/
}
