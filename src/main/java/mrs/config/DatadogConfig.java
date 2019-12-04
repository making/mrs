package mrs.config;

import io.smartup.zipkin.DatadogReporter;
import org.springframework.cloud.sleuth.zipkin2.ZipkinAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import zipkin2.Span;
import zipkin2.reporter.Reporter;

@Configuration
@Profile("cloud")
public class DatadogConfig {

    @Bean(name = ZipkinAutoConfiguration.REPORTER_BEAN_NAME)
    public Reporter<Span> reporter() {
        return new DatadogReporter();
    }
}