package mrs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Order(-100)
@ConfigurationProperties(prefix = "actuator")
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String username;

    private final String password;


    public ActuatorSecurityConfig(@DefaultValue("actuator") String username, @DefaultValue("actuator") String password) {
        this.username = username;
        this.password = password;
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
            .withUser(this.username)
            .password("{noop}" + this.password)
            .roles("ACTUATOR");
    }
}
