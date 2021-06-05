package hu.progmasters.dinopark.repository;

import hu.progmasters.dinopark.domain.Dinosaur;
import hu.progmasters.dinopark.domain.DinosaurType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DinosaurRepositoryH2JdbcTemplate implements DinosaurRepository {

    private final JdbcTemplate jdbcTemplate;

    public DinosaurRepositoryH2JdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createTable();
    }

    public void createTable() {
        jdbcTemplate.execute("DROP TABLE dinosaur IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE dinosaur (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "breed VARCHAR(50) NOT NULL," +
                "type VARCHAR(20))");
    }

    @Override
    public Dinosaur save(Dinosaur dinosaur) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO dinosaur (name, breed, type) VALUES (?,?,?) "
            );
            ps.setString(1, dinosaur.getName());
            ps.setString(2, dinosaur.getBreed());
            ps.setString(3, dinosaur.getType().name());
            return ps;
        }, keyHolder);
        return dinosaur.setId(keyHolder.getKey().intValue());
    }

    @Override
    public List<Dinosaur> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM dinosaur",
                (rs, rn) -> dinosaurMapper(rs));
    }

    @Override
    public List<Dinosaur> findAllByType(DinosaurType dinosaurType) {
        return jdbcTemplate.query(
                "SELECT * FROM dinosaur where type = ?",
                (rs, rn) -> dinosaurMapper(rs),
                dinosaurType);
    }

    private Dinosaur dinosaurMapper(ResultSet rs) throws SQLException {
        return new Dinosaur()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setBreed(rs.getString("breed"))
                .setType(DinosaurType.valueOf(rs.getString("type").toUpperCase()));
    }
}
