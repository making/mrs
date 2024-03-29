package mrs.user;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

	private final JdbcClient jdbcClient;

	public UserRepository(JdbcClient jdbcClient) {
		this.jdbcClient = jdbcClient;
	}

	public Optional<User> findById(String username) {
		return this.jdbcClient
			.sql("SELECT user_id, first_name, last_name, password, role_name FROM usr WHERE  user_id=?")
			.params(username)
			.query((rs, i) -> UserBuilder.user()
				.userId(rs.getString("user_id"))
				.password(rs.getString("password"))
				.roleName(RoleName.valueOf(rs.getString("role_name")))
				.firstName(rs.getString("first_name"))
				.lastName(rs.getString("last_name"))
				.build())
			.optional();
	}

}
