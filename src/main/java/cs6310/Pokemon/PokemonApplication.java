package cs6310.Pokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cs6310.Pokemon.service.CommandService;

@SpringBootApplication(scanBasePackages = { "cs6310.Pokemon.service", "cs6310.Pokemon.dto" })
public class PokemonApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(PokemonApplication.class, args);
		var commandService = context.getBean(CommandService.class);
		commandService.ProcessCommands(args);
	}

}
