// FILE: Battle.js
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { useEffect } from 'react';
import './Battle.css';

const Battle = () => {
  const [selectedPokemon, setSelectedPokemon] = useState([]);
  const [pokemonList, setPokemonList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const handleSelect = (event) => {
    const selectedOptions = Array.from(event.target.selectedOptions, option => option.value);
    setSelectedPokemon(selectedOptions);
  };

  const startBattle = () => {
    if (selectedPokemon.length !== 2) {
      alert('Please select exactly two Pokemon to start the battle.');
      return;
    }
    console.log('Starting battle between:', selectedPokemon);
    // Add your battle logic here
  };

  useEffect(() => {
    const fetchPokemonInfo = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/commands/getAllPokemon`);
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        console.log('data: ' + JSON.stringify(data));
        setPokemonList(data);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };
    fetchPokemonInfo();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  return (

    <><h2>Select Pokemon for Battle</h2><p>Select two Pokemon to start the battle</p><div className="battle-select">
      <select multiple={true} onChange={handleSelect}>
        {pokemonList.map((pokemon, index) => (
          <option key={index} value={pokemon}>
            {pokemon}
          </option>
        ))}
      </select>
      <button onClick={startBattle} className="start-battle-button">Start Battle</button>
    </div></>
  );
};
Battle.propTypes = {
  pokemonList: PropTypes.arrayOf(
    PropTypes.shape({
      name: PropTypes.string.isRequired
    })
  ).isRequired
};

export default Battle;