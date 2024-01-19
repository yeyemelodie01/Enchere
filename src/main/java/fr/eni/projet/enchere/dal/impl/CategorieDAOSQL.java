package fr.eni.projet.enchere.dal.impl;

import fr.eni.projet.enchere.bo.Categorie;
import fr.eni.projet.enchere.dal.CategorieDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("sql")
public class CategorieDAOSQL implements CategorieDAO {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CategorieDAOSQL(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Categorie find(Integer id) {
        String sql = "SELECT * FROM CATEGORIES WHERE no_categorie = :idCategorie;";

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("idCategorie", id);

        Categorie categorie = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, new BeanPropertyRowMapper<>(Categorie.class));
        return categorie;
    }

    @Override
    public List<Categorie> findAll() {
        String sql = "SELECT * FROM CATEGORIES;";

        List<Categorie> categories = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Categorie.class));

        System.out.println(categories);
        return categories;
    }
}
