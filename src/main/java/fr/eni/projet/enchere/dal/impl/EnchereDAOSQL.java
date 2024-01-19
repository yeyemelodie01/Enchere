package fr.eni.projet.enchere.dal.impl;

import fr.eni.projet.enchere.bo.Enchere;
import fr.eni.projet.enchere.dal.EnchereDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile("sql")
public class EnchereDAOSQL implements EnchereDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EnchereDAOSQL(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Enchere find(Integer idUser) {
        String sql = "SELECT * FROM ENCHERES WHERE no_utilisateur = : idUser;";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("idUser", idUser);

        Enchere enchere = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new BeanPropertyRowMapper<>(Enchere.class));
        return enchere;
    }
}
