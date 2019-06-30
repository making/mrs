package mrs;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecutiryConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/js/**", "/css/**").permitAll()
				.antMatchers("/**").authenticated().and().formLogin()
				.loginPage("/loginForm").loginProcessingUrl("/login")
				.usernameParameter("username").passwordParameter("password")
				.defaultSuccessUrl("/rooms", true).failureUrl("/loginForm?error=true")
				.permitAll();
	}
}
