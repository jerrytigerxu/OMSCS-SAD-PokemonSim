package cs6310.Pokemon.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = { "cs6310.Pokemon.service", "cs6310.Pokemon.dto",
        "cs6310.Pokemon.controller" })
@EnableJpaRepositories(basePackages = "cs6310.Pokemon.repository")
@EnableRetry
@EntityScan(basePackages = "cs6310.Pokemon.model.entity")
@EnableScheduling
public class PokemonApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(PokemonApplication.class, args);
        var commandParser = context.getBean(CommandParser.class);
        commandParser.processCommands(args);
    }

}
