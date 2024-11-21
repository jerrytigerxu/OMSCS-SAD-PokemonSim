// FILE: getAllPokemon.js
import React, { useState, useEffect } from 'react';

const GetAllPokemon = ({ onSelect }) => {
    const [pokemonList, setPokemonList] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchPokemonList = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/commands/getAllPokemon');
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const data = await response.json();
                setPokemonList(data);
            } catch (error) {
                setError(error);
            } finally {
                setLoading(false);
            }
        };

        fetchPokemonList();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error.message}</div>;
    }

    return (
        <div>
            <label htmlFor="pokemon-select">Choose a Pokemon:</label>
            <select id="pokemon-select" onChange={(e) => onSelect(e.target.value)}>
                <option value="">--Please choose a Pokemon--</option>
                {pokemonList.map((pokemon, index) => (
                    <option key={index} value={pokemon}>
                        {pokemon}
                    </option>
                ))}
            </select>
        </div>
    );
};

export default GetAllPokemon;