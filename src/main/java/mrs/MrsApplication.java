package mrs;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MrsApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(MrsApplication.class).applicationStartup(new BufferingApplicationStartup(2048))
			.run(args);
	}

}
