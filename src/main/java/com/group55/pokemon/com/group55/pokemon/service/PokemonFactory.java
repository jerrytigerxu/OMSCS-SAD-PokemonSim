package com.group55.pokemon.service;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.group55.pokemon.dto.Pokemon;
import com.group55.pokemon.dto.PokemonImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PokemonFactory {

    private final ApplicationContext applicationContext;

    public Pokemon createPokemon(String name)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            var constructor = PokemonImpl.class.getConstructor(String.class);
            Pokemon pokemon = (Pokemon) constructor.newInstance(name);
            registerPokemonBean(name, pokemon);
            return pokemon;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void registerPokemonBean(String name, Pokemon pokemon) {
        ConfigurableBeanFactory beanFactory = (ConfigurableBeanFactory) applicationContext
                .getAutowireCapableBeanFactory();
        beanFactory.registerSingleton(name, pokemon);
    }
}