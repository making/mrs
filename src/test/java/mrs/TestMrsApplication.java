package mrs;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;

@TestConfiguration(proxyBeanMethods = false)
public class TestMrsApplication {

	@Bean
	@ServiceConnection
	MySQLContainer<?> mysqlContainer() {
		return new MySQLContainer<>(DockerImageName.parse("mysql:latest"));
	}

	public static void main(String[] args) {
		SpringApplication.from(MrsApplication::main).with(TestMrsApplication.class).run(args);
	}

}
