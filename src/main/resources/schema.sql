CREATE TABLE UTILISATEURS (
                              no_utilisateur   INTEGER AUTO_INCREMENT NOT NULL,
                              pseudo           VARCHAR(30) NOT NULL,
                              nom              VARCHAR(30) NOT NULL,
                              prenom           VARCHAR(30) NOT NULL,
                              email            VARCHAR(255) NOT NULL,
                              telephone        VARCHAR(15),
                              rue              VARCHAR(30) NOT NULL,
                              code_postal      VARCHAR(10) NOT NULL,
                              ville            VARCHAR(30) NOT NULL,
                              mot_de_passe     VARCHAR(30) NOT NULL,
                              credit           INTEGER NOT NULL,
                              [role]			VARCHAR(30) NOT NULL
)