package cs6310.Pokemon.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication(scanBasePackages = { "cs6310.Pokemon.service", "cs6310.Pokemon.dto",
		"cs6310.Pokemon.controller" })
@EnableJpaRepositories(basePackages = "cs6310.Pokemon.repository")
@EnableRetry
public class PokemonApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(PokemonApplication.class, args);
		var commandService = context.getBean(CommandService.class);
		commandService.ProcessCommands(args);
	}

}
