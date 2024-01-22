package fr.eni.projet.enchere.controller;

import fr.eni.projet.enchere.bll.IntUtilisateurService;
import fr.eni.projet.enchere.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/encheres")
@SessionAttributes({"utilisateur"})
public class EnchereController {

    IntUtilisateurService intUtilisateurService;

    public EnchereController(IntUtilisateurService intUtilisateurService) {
        this.intUtilisateurService = intUtilisateurService;
    }

    // Mise en session de la liste des utilisateurs
    @ModelAttribute("utilisateur")
    public List<Utilisateur> findAll() {
        return this.intUtilisateurService.findAll();
    }

    // Mapping de la page d'accueil
    @GetMapping
    public String Index(Model model) {
        List<Utilisateur> utilisateurList = this.intUtilisateurService.findAll();
        model.addAttribute("users", utilisateurList);
        System.out.println(utilisateurList);
        return "index";
    }

    // Mapping de la page profil
    @GetMapping("/profile")
    public String viewProfile(@RequestParam(name = "id") Integer idUser) {
        System.out.println("idUser: " + idUser);
        return "profile";
    }
}

