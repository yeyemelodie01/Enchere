package fr.eni.projet.enchere.controller;

import fr.eni.projet.enchere.bll.IntArticleService;
import fr.eni.projet.enchere.bll.IntCategorieService;
import fr.eni.projet.enchere.bll.IntEnchereService;
import fr.eni.projet.enchere.bll.IntUtilisateurService;
import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Categorie;
import fr.eni.projet.enchere.bo.Enchere;
import fr.eni.projet.enchere.bo.Utilisateur;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/encheres")
public class EnchereController {

    IntUtilisateurService intUtilisateurService;
    IntCategorieService intCategorieService;
    IntArticleService intArticleService;
    IntEnchereService intEnchereService;

    public EnchereController(IntUtilisateurService intUtilisateurService, IntCategorieService intCategorieService, IntArticleService intArticleService, IntEnchereService intEnchereService) {
        this.intUtilisateurService = intUtilisateurService;
        this.intCategorieService = intCategorieService;
        this.intArticleService = intArticleService;
        this.intEnchereService = intEnchereService;
    }

    /**
     * Mapping de la page d'accueil
     */
    @GetMapping
    public String Index() {
        return "index";
    }


    /**
     * Mapping de la page profil
     */
    @GetMapping("/profile")
    public String viewProfile(@RequestParam(name = "id") Integer idUser) {
        return "profile";
    }

    /**
     * Mapping de la modification de profil
     */
    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute("utilisateur") Utilisateur utilisateur) {
        this.intUtilisateurService.update(utilisateur);
        return "redirect:/encheres";
    }


    /**
     * Mapping de la suppression de profil
     */
    @GetMapping("/profile/delete")
    public String deleteProfile(@RequestParam(name = "id") Integer idUser, HttpServletRequest request) {
        this.intUtilisateurService.delete(idUser);
        System.out.println("Utilisateur à supprimer :" + idUser);

        // Invalider la session actuelle
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/encheres";
    }

    /**
     * Mapping de l'affichage d'un article
     */
    @GetMapping("/article")
    public String viewPageArticle(Model model){
        List<Categorie> categories = this.intCategorieService.findAll();
        model.addAttribute("categories", categories);
        return "create-article";
    }

    /**
     * Mapping de création d'un article
     */
    @PostMapping("/addArticle")
    public String addArticle(
            @RequestParam(name = "articleName") String articleName,
            @RequestParam(name = "description") String description,
            @RequestParam(name = "categorie") Integer idCategorie,
            @RequestParam(name = "miseaprix") Integer miseaprix,
            @RequestParam(name = "startdate") LocalDate startdate,
            @RequestParam(name = "endDate") LocalDate endDate,
            @RequestParam(name = "idUser") Integer idUser
    ){
        Utilisateur utilisateur = this.intUtilisateurService.findById(idUser);
        Categorie categorie = this.intCategorieService.findById(idCategorie);
        Article article = new Article(articleName, description, startdate, endDate, miseaprix, 0, utilisateur,categorie);
        System.out.println("nouvel article: " + article);
        this.intArticleService.create(article);
        return "redirect:/encheres";
    }

    @GetMapping("/detailVente")
    public String saleDetail (
            @RequestParam(name = "id") Integer idArticle, Model model
    ) {
        Article article = this.intArticleService.findById(idArticle);
//        Enchere enchere = this.intEnchereService.find(article);
        System.out.println("Récupération de l'id de l'article: " + idArticle);
        System.out.println("Article trouver: " + article);
//        System.out.println("Enchère trouver: " + enchere);
        return "detail-vente";
    }

}

