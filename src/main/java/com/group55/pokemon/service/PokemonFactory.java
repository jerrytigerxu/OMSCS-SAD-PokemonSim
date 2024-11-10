package com.group55.pokemon.service;

import java.lang.reflect.InvocationTargetException;

import org.springframework.stereotype.Service;

import com.group55.pokemon.dto.Pokemon;
import com.group55.pokemon.dto.PokemonImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PokemonFactory {

    public Pokemon createPokemon(String name) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            var constructor = PokemonImpl.class.getConstructor(String.class);
            return (Pokemon) constructor.newInstance(name);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
