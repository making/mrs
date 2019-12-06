package mrs.user;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> findById(String username) {
        try {
            final User user = this.jdbcTemplate.queryForObject("SELECT user_id, first_name, last_name, password, role_name FROM usr WHERE  user_id=?",
                (rs, i) -> {
                    final User u = new User();
                    u.setUserId(rs.getString("user_id"));
                    u.setFirstName(rs.getString("first_name"));
                    u.setLastName(rs.getString("last_name"));
                    u.setPassword(rs.getString("password"));
                    u.setRoleName(RoleName.valueOf(rs.getString("role_name")));
                    return u;
                }, username);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
