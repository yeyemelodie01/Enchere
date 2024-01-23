package fr.eni.projet.enchere.controller;

import fr.eni.projet.enchere.bll.IntArticleService;
import fr.eni.projet.enchere.bll.IntCategorieService;
import fr.eni.projet.enchere.bll.IntUtilisateurService;
import fr.eni.projet.enchere.bo.Article;
import fr.eni.projet.enchere.bo.Categorie;
import fr.eni.projet.enchere.bo.Utilisateur;
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

    public EnchereController(IntUtilisateurService intUtilisateurService, IntCategorieService intCategorieService, IntArticleService intArticleService) {
        this.intUtilisateurService = intUtilisateurService;
        this.intCategorieService = intCategorieService;
        this.intArticleService = intArticleService;
    }

    @GetMapping
    public String Index() {
        return "index";
    }

    @GetMapping("/profile")
    public String viewProfile(@RequestParam(name = "id") Integer idUser) {
        System.out.println("idUser: " + idUser);
        return "profile";
    }

    @GetMapping("/article")
    public String viewPageArticle(Model model){
        List<Categorie> categories = this.intCategorieService.findAll();
        model.addAttribute("categories", categories);
        return "create-article";
    }
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
    public String saleDetail () {
        return "detail-vente";
    }

}

