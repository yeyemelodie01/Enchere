package fr.eni.projet.enchere.controller;

import fr.eni.projet.enchere.bll.IntUtilisateurService;
import fr.eni.projet.enchere.bo.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/signin")
public class SigninController {

    private IntUtilisateurService intUtilisateurService;

    public SigninController(IntUtilisateurService intUtilisateurService) {
        this.intUtilisateurService = intUtilisateurService;
    }

    // Requête GET pour l'affichage du formulaire d'inscription d'un nouvel utilisateur
    @GetMapping
    public String signIn(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "signin";
    }

    @PostMapping
    public String registerNewUser(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model){
        this.intUtilisateurService.create(utilisateur);
        model.addAttribute("utilisateur", utilisateur);
        System.out.println(utilisateur);
        return "redirect:/encheres";

    }
}
