package mrs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Order(-100)
@Configuration
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

	private final ActuatorProps props;

	public ActuatorSecurityConfig(ActuatorProps props) {
		this.props = props;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.mvcMatcher("/actuator/prometheus").authorizeRequests()
				.mvcMatchers("/actuator/prometheus").hasRole("ACTUATOR")
				.and()
				.httpBasic()
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser(this.props.getUsername())
				.password("{noop}" + this.props.getPassword())
				.roles("ACTUATOR");
	}
}
