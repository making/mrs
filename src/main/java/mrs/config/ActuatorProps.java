package mrs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "actuator")
public class ActuatorProps {

	private final String password;

	private final String username;


	public ActuatorProps(@DefaultValue("actuator") String username, @DefaultValue("actuator") String password) {
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
}
