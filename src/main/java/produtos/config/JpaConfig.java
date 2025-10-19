package produtos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import produtos.entity.User;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class JpaConfig {

    @Bean
    public AuditorAware<User> auditorAwareImpl() {
        return new AuditorAwareImpl();
    }
}

