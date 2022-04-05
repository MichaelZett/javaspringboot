package de.zettsystems.config;

import de.zettsystems.domain.DataRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public DataRepository helloWorld() {
        return new DataRepository();
    }
}
