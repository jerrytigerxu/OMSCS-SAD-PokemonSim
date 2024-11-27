package cs6310.Pokemon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cs6310.Pokemon.dto.BattleResult;
import cs6310.Pokemon.dto.TournamentResult;
import cs6310.Pokemon.exception.InvalidSeedException;
import cs6310.Pokemon.service.CommandService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/commands")
public class CommandController {

    @Autowired
    private CommandService commandService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/battle/{pokemon1}/{pokemon2}")
    public ResponseEntity<String> handleBattle(@PathVariable String pokemon1, @PathVariable String pokemon2) {
        System.out.println("Starting battle between " + pokemon1 + " and " + pokemon2);
        
        String result;
        try {
            BattleResult battleResult = commandService.doBattle(pokemon1, pokemon2);
            result = objectMapper.writeValueAsString(battleResult);
        } catch (InvalidSeedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body("Error processing JSON");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing battle");
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/tournament/{pokemonList}")
    public ResponseEntity<String> handleTournament(@PathVariable String pokemonList) {
        // Split the comma-delimited list into a list of strings
        List<String> pokemonListParsed = Arrays.asList(pokemonList.split(","));
        String result;

        try {
            TournamentResult tournamentResult = commandService.doTournament(pokemonListParsed);
            result = objectMapper.writeValueAsString(tournamentResult);
            //System.out.println("result: " + result);
        } catch (InvalidSeedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body("Error processing JSON");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing tournament");
        }
        
        /* 
        try {
            result = commandService.doTournament(pokemonListParsed).toString();
            System.out.println("result: "+result);
        } catch (InvalidSeedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
            */
        
        return ResponseEntity.ok(result);
    }

    @GetMapping("/displayInfo/{pokemon}")
    public ResponseEntity<String> handleDisplayInfo(@PathVariable String pokemon) {
        String result = commandService.doDisplayInfo(pokemon);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/setSeed")
    public ResponseEntity<String> handleSetSeed(@RequestBody Map<String, Integer> setSeedRequest) {
        System.out.println("Trying to set seed to: " + setSeedRequest.get("seed"));

        String result;
        try {
            result = commandService.doSetSeed(setSeedRequest.get("seed"));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

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
