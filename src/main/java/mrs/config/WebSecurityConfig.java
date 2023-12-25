package mrs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/js/**", "/css/**").permitAll()
            .antMatchers("/**").authenticated().and().formLogin()
            .loginPage("/loginForm").loginProcessingUrl("/login")
            .usernameParameter("username").passwordParameter("password")
            .defaultSuccessUrl("/rooms").failureUrl("/loginForm?error=true")
            .permitAll();
        return http.build();
    }
}
