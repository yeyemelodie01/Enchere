package fr.eni.projet.enchere.securite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration
public class ENISecuriteConfig {

    /* Récupération des utilisateur dans la base de données */
    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        /* setUsersByUsernameQuery a besoin de 3 colonne donc rajouter true ou 1 */
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT pseudo, mot_de_passe, 1 FROM UTILISATEURS WHERE pseudo = ?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT pseudo, role FROM UTILISATEURS WHERE pseudo = ?");
        return jdbcUserDetailsManager;
    }
    /* Restriction des URLs selon la connexion utilisateur */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            // Permet à l'employe d'accéder à la liste des formateurs
            //Alternative dans le cas ou un seul role est spécifié pour un utilisateur
            // auth.requestMatchers(HttpMethod.GET,"/formateurs").hasRole("EMPLOYE", "FORMATEUR","ADMIN")
            auth.requestMatchers(HttpMethod.GET,"/encheres/profile/*").hasRole("UTILISATEUR")
                .requestMatchers(HttpMethod.GET,"/encheres/article/*").hasRole("UTILISATEUR")
                .requestMatchers(HttpMethod.POST,"/encheres/addArticle/*").hasRole("UTILISATEUR");
                    //Permet au formateur de manipuler les détails
                    /*.requestMatchers(HttpMethod.GET,"/formateurs/detail").hasRole("FORMATEUR")
                    .requestMatchers(HttpMethod.POST,"/formateurs/detailPost").hasRole("FORMATEUR")

                    //Permet aux Admin de creer un formateur
                    .requestMatchers("/formateurs/creer").hasRole("ADMIN");*/

            //Permet à tous le monde d'avoir accès à la page d'accueil
            auth.requestMatchers("/encheres/*").permitAll()
                .requestMatchers("/signin").permitAll();

                    //Permet à tous le monde d'avoir accès aux ressources
            auth.requestMatchers("/css/*").permitAll()
                .requestMatchers("/images/*").permitAll();
            // Toutes autres url ne sont pas autorisés
            auth.anyRequest().denyAll();


        });
        //formulaire de connexion par defaut
        http.formLogin(form -> {
            //Configuration de la page login
            form.loginPage("/login").permitAll();

            //page afficher par défaut lors de la connexion
            form.defaultSuccessUrl("/encheres");
            form.failureForwardUrl("/error");
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