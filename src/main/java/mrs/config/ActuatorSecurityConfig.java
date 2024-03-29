package mrs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Order(-100)
@Configuration
public class ActuatorSecurityConfig {

	private final ActuatorProps props;

	public ActuatorSecurityConfig(ActuatorProps props) {
		this.props = props;
	}

	@Bean
	SecurityFilterChain filterChainForActuator(HttpSecurity http) throws Exception {
		http.securityMatcher("/actuator/*")
			.authorizeHttpRequests(requests -> requests.requestMatchers("/actuator/startup")
				.permitAll()
				.requestMatchers("/actuator/prometheus")
				.hasRole("ACTUATOR"))
			.httpBasic(withDefaults())
			.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.csrf(csrf -> csrf.disable());
		return http.build();
	}

	@Bean
	InMemoryUserDetailsManager inMemoryAuthManager() throws Exception {
		return new InMemoryUserDetailsManager(User.builder()
			.username(this.props.getUsername())
			.password("{noop}" + this.props.getPassword())
			.roles("ACTUATOR")
			.build());
	}

}
