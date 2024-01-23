package fr.eni.projet.enchere.securite;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;


@Configuration
@EnableWebSecurity
public class ENISecuriteConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception { // Restriction des URLs selon la connexion utilisateur
        http.authorizeHttpRequests(auth -> {
            // Permet à l'employe d'accéder à la liste des formateurs
            //Alternative dans le cas ou un seul role est spécifié pour un utilisateur

            //Permet à tous le monde d'avoir accès à la page d'accueil
            auth.requestMatchers("/encheres/*").permitAll()
                    .requestMatchers(HttpMethod.GET,"/encheres/article").authenticated()
                    .requestMatchers(HttpMethod.POST, "/encheres/profile").authenticated()
                    .requestMatchers(HttpMethod.POST, "/encheres/detailVente").authenticated();


            //Permet à tous le monde d'avoir accès aux ressources
            auth.requestMatchers("/signin").permitAll()
                .requestMatchers("/css/*").permitAll()
                .requestMatchers("/images/*").permitAll()
                .requestMatchers("/error").permitAll();

            // Toutes autres url ne sont pas autorisés
            auth.anyRequest().authenticated();
        });


        //formulaire de connexion par defaut
        http.formLogin(form -> {
            //Configuration de la page login
            form.loginPage("/login").permitAll();

            //page afficher par défaut lors de la connexion
            form.defaultSuccessUrl("/encheres");
            form.failureUrl("/loginError");

            // permet de définir ce qu'il se passe lorsque le login est validé
            form.successHandler(new SavedRequestAwareAuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(HttpServletRequest request,
                                                    HttpServletResponse response, Authentication authentication)
                                                    throws IOException, ServletException {
                    //* permet de mettre l'utilisateur connecté dans une variable de session currentUser *//
                    if(authentication!=null) {
                        MyUserDetail userDetails = (MyUserDetail) authentication.getPrincipal();
                        request.getSession().setAttribute("currentUser", userDetails.getUtilisateur());
                    }
                    super.onAuthenticationSuccess(request, response, authentication);
                }

            });
        });



        //gestion du logout
        http.logout(logout ->
                //Suppression de la session côté serveur
                logout.invalidateHttpSession(true)
                        .clearAuthentication(true)
                        //Supprimer les cookies
                        .deleteCookies("JSESSIONID")
                        //Déterminer quelle page utiliser pour le logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        //Chemin par défaut en cas de success
                        .logoutSuccessUrl("/encheres")
                        .permitAll()
        );
        return http.build();
    }
}