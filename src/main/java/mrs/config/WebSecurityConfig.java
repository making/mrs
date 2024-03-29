package mrs.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(requests -> requests.requestMatchers("/js/**", "/css/**")
			.permitAll()
			.requestMatchers("/**")
			.authenticated())
			.formLogin(login -> login.loginPage("/loginForm")
				.loginProcessingUrl("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.defaultSuccessUrl("/rooms")
				.failureUrl("/loginForm?error=true")
				.permitAll());
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return new ProviderManager(authenticationProvider);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	@Primary
	public CompositeUserDetailsService compositeUserDetailsService(List<UserDetailsService> userDetailsServices) {
		return new CompositeUserDetailsService(userDetailsServices);
	}

	public static class CompositeUserDetailsService implements UserDetailsService {

		private final List<UserDetailsService> delegates;

		public CompositeUserDetailsService(List<UserDetailsService> delegates) {
			this.delegates = delegates;
		}

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			for (UserDetailsService delegate : delegates) {
				try {
					final UserDetails userDetails = delegate.loadUserByUsername(username);
					if (userDetails != null) {
						return userDetails;
					}
				}
				catch (UsernameNotFoundException ignored) {
					//
				}
			}
			throw new UsernameNotFoundException("The requested user (%s) is not found.".formatted(username));
		}

	}

}
