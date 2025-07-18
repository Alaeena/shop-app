package app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
        "app.dao",
        "app.repository.postgres"
})
public class JpaConfig {
}
