package ru.job4j.caraccident.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.caraccident.model.Accident;
import ru.job4j.caraccident.model.AccidentType;
import ru.job4j.caraccident.model.Rule;

import java.sql.PreparedStatement;
import java.util.*;

@Repository
public class AccidentJdbcTemplate implements AccidentRepository {

    private final JdbcTemplate jdbc;

    private static final Logger LOG =
            LoggerFactory.getLogger(AccidentJdbcTemplate.class.getName());

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void save(Accident accident) {
        if (accident.getId() == 0) {
            createAccident(accident);
        } else {
            updateAccident(accident);
        }
        accident.getRules().forEach(rule ->
                jdbc.update("insert into accident_rule (accident_id, rule_id) values (?, ?)",
                        accident.getId(), rule.getId()));
    }

    public List<Accident> getAll() {

        String sql = "select a.id, a.name, a.text, a.address, t.name as type_name, t.id as type_id "
                + "from accident a join type t on a.type_id = t.id";

        List<Accident> accidentList = jdbc.query(sql, getAccidentRowMapper());
        accidentList.sort(Comparator.comparing(Accident::getId));
        return accidentList;
    }

    @Override
    public void createAccident(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into accident(name, text, address, type_id) values(?, ?, ?, ?)",
                    new String[]{"id"}
            );
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId((Integer) keyHolder.getKey());
    }

    public void updateAccident(Accident accident) {
        jdbc.update(
                "update accident set name = ?, text = ?, address = ?, type_id = ? where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId(), accident.getId()
        );
        jdbc.update("delete from accident_rule where accident_id = ?", accident.getId());
    }

    @Override
    public Accident findAccidentById(int id) {
        String sql = "select * from accident where id = ?";
        Accident accident = jdbc.queryForObject(sql, getAccidentRowMapper(), id);
        accident.setRules(new HashSet<>(getRulesByAccidentId(id)));
        return accident;
    }

    @Override
    public List<AccidentType> getAllAccidentTypes() {
        String sql = "select id, name from type";
        return jdbc.query(sql, getAccidentTypeRowMapper());
    }

    @Override
    public AccidentType findAccidentTypeById(int id) {
        String sql = "select id, name from type where id = ?";
        return jdbc.queryForObject(sql, getAccidentTypeRowMapper(), id);
    }

    @Override
    public List<Rule> findAllRules() {
        RowMapper<Rule> rowMapper = (rs, row) -> Rule.of(
                rs.getInt("id"),
                rs.getString("name")
        );
        String sql = "select id, name from rule";
        return jdbc.query(sql, rowMapper);
    }

    @Override
    public Rule findRuleById(int id) {
        String sql = "select * from rule where id = ?";
        return jdbc.queryForObject(sql, getRuleRowMapper(), id);
    }

    @Override
    public Set<Rule> getRulesForAccident(List<Integer> ids) {
        String iSql = String.join(",", Collections.nCopies(ids.size(), "?"));
        String sql = String.format("select id, name from rule where id in (%s)", iSql);
        System.out.println(sql);
        return new HashSet<>(jdbc.query(sql, ids.toArray(), getRuleRowMapper()));
    }

    public List<Rule> getRulesByAccidentId(int id) {
        String sql = "select r.id, r.name from rule r"
                + " join accident_rule ar on r.id = ar.rule_id"
                + " where ar.accident_id = ?";
        RowMapper<Rule> rowMapper = (rs, row) ->
                Rule.of(rs.getInt("id"), rs.getString("name"));

        return jdbc.query(sql, rowMapper, id);
    }

    private RowMapper<AccidentType> getAccidentTypeRowMapper() {
        return (rs, row) -> AccidentType.of(
                rs.getInt("id"),
                rs.getString("name")
        );
    }

    private RowMapper<Rule> getRuleRowMapper() {
        return (rs, row) -> Rule.of(
                rs.getInt("id"),
                rs.getString("name")
        );
    }

    private RowMapper<Accident> getAccidentRowMapper() {
        return (rs, row) -> {
            AccidentType accidentType = AccidentType.of(
                    rs.getInt("id"),
                    rs.getString("name")
            );
            int id = rs.getInt("id");
            Accident accident = new Accident(
                    id,
                    rs.getString("name"),
                    rs.getString("text"),
                    rs.getString("address")
            );
            accident.setType(accidentType);
            List<Rule> rulesByAccidentId = getRulesByAccidentId(id);
            accident.setRules(new HashSet<>(rulesByAccidentId));
            return accident;
        };
    }
}
