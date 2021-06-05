package hu.progmasters.dinopark.repository;

import hu.progmasters.dinopark.domain.DinosaurType;
import hu.progmasters.dinopark.domain.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class VisitorRepositoryH2JdbcTemplate implements VisitorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VisitorRepositoryH2JdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createTable();
    }

    private void createTable() {
        jdbcTemplate.execute("DROP TABLE visitor IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE visitor (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "preferred_dino VARCHAR(50) NOT NULL," +
                "rating INT NOT NULL)");
    }

    @Override
    public Visitor save(Visitor visitor) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO visitor (name, preferred_dino, rating) VALUES (?,?,?)"
            );
            ps.setString(1, visitor.getName());
            ps.setString(2, visitor.getPreferred().name());
            ps.setInt(3, visitor.getRating());
            return ps;
        }, keyHolder);
        return visitor.setId(keyHolder.getKey().intValue());
    }

    @Override
    public List<Visitor> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM visitor", (rs, rn) -> visitorMapper(rs));
    }

    private Visitor visitorMapper(ResultSet rs) throws SQLException {
        return new Visitor()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setPreferred(DinosaurType.valueOf(rs.getString("preferred_dino").toUpperCase()))
                .setRating(rs.getInt("rating"));
    }
}
