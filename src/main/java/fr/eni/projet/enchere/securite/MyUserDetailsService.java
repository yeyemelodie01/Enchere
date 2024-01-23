package fr.eni.projet.enchere.securite;

import fr.eni.projet.enchere.bll.IntUtilisateurService;
import fr.eni.projet.enchere.bo.Utilisateur;

import fr.eni.projet.enchere.dal.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurDAO utilisateurDAO;

    @Override
    public UserDetails loadUserByUsername(String pseudo) {

        Utilisateur utilisateur;

        try {
            utilisateur = this.utilisateurDAO.findByPseudo(pseudo);
        } catch (Exception e) {
            throw new UsernameNotFoundException(pseudo);
        }

        return new MyUserDetail(utilisateur);
    }


}
