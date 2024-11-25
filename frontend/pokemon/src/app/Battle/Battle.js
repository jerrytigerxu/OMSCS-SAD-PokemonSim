// FILE: Battle.js
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { useEffect } from 'react';
import './Battle.css';

const Battle = () => {
  const [pokemon1, setPokemon1] = useState('');
  const [pokemon2, setPokemon2] = useState('');
  const [pokemonList, setPokemonList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [battleResults, setBattleResults] = useState(null);

  const handleSelect1 = (event) => {
    const selectedOptions = Array.from(event.target.selectedOptions, option => option.value);
    setPokemon1(selectedOptions);
  };

  const handleSelect2 = (event) => {
    const selectedOptions = Array.from(event.target.selectedOptions, option => option.value);
    setPokemon2(selectedOptions);
  };

  const startBattle = async () => {
    console.log('pokeon1: ' + pokemon1);
    console.log('pokemon2: ' + pokemon2);
    if (pokemon1.length !== 1 || pokemon2.length !== 1) {
      alert('Please select exactly two Pokemon to start the battle.');
      return;
    }
    console.log('Starting battle between ' + pokemon1 + ' and ' + pokemon2);

    // TODO: Add a fetch call to check that the seed is set before allowing battle to run
    const seedResponse = await fetch(`http://localhost:8080/api/commands/isSeedSet`);
    const seedData = await seedResponse.json();
    if (!seedData) {
      alert('Please set a seed before starting the battle.');
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/api/commands/battle/${pokemon1}/${pokemon2}`);
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      console.log('Battle data:', data);
      setBattleResults(data);
    } catch (error) {
      console.log(error.message);
      setError(error.message);
    }
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

    <><h2>Select Pokemon for Battle</h2>
      <p>Select two Pokemon to start the battle</p><div className="battle-select">
        <select multiple={false} onChange={handleSelect1}>
          {pokemonList.map((pokemon, index) => (
            <option key={index} value={pokemon}>
              {pokemon}
            </option>
          ))}
        </select>
        <select multiple={false} onChange={handleSelect2}>
          {pokemonList.map((pokemon, index) => (
            <option key={index} value={pokemon}>
              {pokemon}
            </option>
          ))}
        </select>
        <button onClick={startBattle} className="start-battle-button">Start Battle</button>
      </div>
      
      {battleResults && (
        <div className="battle-results"> 
          <h3>Battle Results:</h3>
          <p>Winner: {battleResults.winnerPokemon}</p>
          <p>Loser: {battleResults.loserPokemon}</p>

          <h4>Battle History:</h4>
          <ul>
            {battleResults.battleHistory.map((event, index) => (
              <li key={index}>{event}</li>
            ))}
          </ul>
        </div>
      )}
      </>
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