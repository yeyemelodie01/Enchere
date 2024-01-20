package fr.eni.projet.enchere.controller;

import fr.eni.projet.enchere.bll.IntUtilisateurService;
import fr.eni.projet.enchere.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/encheres")
public class EnchereController {

    IntUtilisateurService intUtilisateurService;

    public EnchereController(IntUtilisateurService intUtilisateurService) {
        this.intUtilisateurService = intUtilisateurService;
    }

    @GetMapping
    public String Index(Model model) {
        List<Utilisateur> utilisateurList = this.intUtilisateurService.getUtilisateurs();
        model.addAttribute("users", utilisateurList);
        System.out.println(utilisateurList);
        return "index";
    }

    @GetMapping("/profile")
    public String viewProfile(@RequestParam(name = "id") Integer idUser) {
        System.out.println("idUser: " + idUser);
        return "profil";
    }
}

