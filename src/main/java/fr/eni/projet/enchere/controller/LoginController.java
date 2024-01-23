package fr.eni.projet.enchere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String Login(){
        return "login";
    }
    @GetMapping("/loginError")
    public String Error(Model model){
        model.addAttribute("loginError", true);
        return "/login";
    }


}
