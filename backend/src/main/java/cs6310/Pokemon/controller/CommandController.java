package cs6310.Pokemon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import cs6310.Pokemon.service.CommandService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/commands")
public class CommandController {

    private final CommandService commandService;

    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @GetMapping("/battle/{pokemon1}/{pokemon2}")
    public ResponseEntity<String> handleBattle(@PathVariable String pokemon1, @PathVariable String pokemon2) {
        System.out.println("Starting battle between " + pokemon1 + " and " + pokemon2);
        //String result = "\"Testing!!!!!!!!!!!!!\"";
        String result = commandService.doBattle(pokemon1, pokemon2).toString();
        System.out.println("Testing: " + result);
        



        return ResponseEntity.ok(result);
    }

    @GetMapping("/tournament/{pokemonList}")
    public ResponseEntity<String> handleTournament(@PathVariable String pokemonList) {
        // Split the comma-delimited list into a list of strings
        List<String> pokemonListParsed = Arrays.asList(pokemonList.split(","));
        String result = commandService.doTournament(pokemonListParsed).toString();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/displayInfo/{pokemon}")
    public ResponseEntity<String> handleDisplayInfo(@PathVariable String pokemon) {
        String result = commandService.doDisplayInfo(pokemon);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/isSeedSet")
    public ResponseEntity<Boolean> isSeedSet() {
        boolean isSet = commandService.isSeedSet();
        return ResponseEntity.ok(isSet);
    }

    @PostMapping("/setSeed")
    public ResponseEntity<String> handleSetSeed(@RequestBody Map<String, Integer> setSeedRequest) {
        System.out.println("Setting seed to: " + setSeedRequest.get("seed"));
        String result = commandService.doSetSeed(setSeedRequest.get("seed"));
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/removeSeed")
    public ResponseEntity<String> handleRemoveSeed() {
        String result = commandService.doRemoveSeed();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/stop")
    public ResponseEntity<String> handleStop() {
        String result = commandService.doStop();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllPokemon")
    public ResponseEntity<List<String>> getAllPokemon() {
        List<String> allPokemon = commandService.getAllPokemon();
        return ResponseEntity.ok(allPokemon);
    }
}
