package de.zettsystems.demo.config;

import de.zettsystems.demo.helloworld.HelloWorldService;
import de.zettsystems.demo.helloworld.HelloWorldServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean // creating a Spring Bean for the AC
    public HelloWorldService helloWorld() {
        return new HelloWorldServiceImpl();
    }
}
