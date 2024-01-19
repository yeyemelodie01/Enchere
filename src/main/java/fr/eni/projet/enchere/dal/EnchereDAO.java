package fr.eni.projet.enchere.dal;

import fr.eni.projet.enchere.bo.Enchere;

public interface EnchereDAO {
    Enchere find(Integer idUser);
}
