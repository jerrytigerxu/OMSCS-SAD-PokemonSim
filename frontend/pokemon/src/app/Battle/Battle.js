// FILE: Battle.js
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { useEffect, useRef } from 'react';
import './Battle.css';

const Battle = () => {
  const [pokemon1, setPokemon1] = useState('');
  const [pokemon2, setPokemon2] = useState('');
  const [pokemonList, setPokemonList] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [result, setResult] = useState('');
  const [orderOfBattle, setOrderOfBattle] = useState('');

  const [battleResults, setBattleResults] = useState(null);

  // state variables for displaying battle history items in "real time"
  const [displayedHistory, setDisplayedHistory] = useState([]);
  const [isPlaying, setIsPlaying] = useState(false);
  const [isStopped, setIsStopped] = useState(true);
  const historyEndRef = useRef(null);

  const handleSelect1 = (event) => {
    const selectedOptions = Array.from(event.target.selectedOptions, option => option.value);
    setPokemon1(selectedOptions);
  };

  const handleSelect2 = (event) => {
    const selectedOptions = Array.from(event.target.selectedOptions, option => option.value);
    setPokemon2(selectedOptions);
  };

  const startBattle = async () => {
    if (pokemon1.length !== 1 || pokemon2.length !== 1) {
      alert('Please select exactly two Pokemon to start the battle.');
      return;
    }

    setError(null);
    setResult('');

    try {
      const response = await fetch(`http://localhost:8080/api/commands/battle/${pokemon1}/${pokemon2}`);
      if (!response.ok) {
        if (response.status === 400) {
          const errorData = JSON.stringify(await response.text());
          throw new Error(errorData || 'Bad Request');
        }
      }
      const data = await response.json();
      const lineDelimitedString = data.orderOfBattle.replace(/,/g, '\n');
      setOrderOfBattle(lineDelimitedString);
      setResult(`Winner: ${data.winnerPokemon}, Loser: ${data.loserPokemon}`);
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
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };
    fetchPokemonInfo();
  }, []);

  // Interval function for displaying the battleHistory in "real time"
  useEffect(() => {
    let intervalId;
    if (isPlaying && !isStopped && battleResults && battleResults.battleHistory.length > 0) {
      let currentIndex = 0;
      intervalId = setInterval(() => {

        console.log("Current event:", battleResults.battleHistory[currentIndex]);
        console.log("Displayed history before update:", displayedHistory);
        console.log(currentIndex);

        setDisplayedHistory(prevHistory => {
          if (currentIndex < battleResults.battleHistory.length) {
            const newHistory = [...prevHistory, battleResults.battleHistory[currentIndex]];
            console.log("Displayed history after update:", newHistory);
            return newHistory;
          } else {
            return prevHistory;
          } 
        });

        currentIndex++;
        if (currentIndex >= battleResults.battleHistory.length) {
          clearInterval(intervalId);
          setIsPlaying(false); // Pause when all events are displayed
        }
      }, 500); // Interval is currently set to 500 milliseconds
    }
    return () => clearInterval(intervalId);
  }, [isPlaying, isStopped, battleResults]);

  useEffect(() => {
    if (historyEndRef.current) {
      historyEndRef.current.scrollIntoView({ behavior: 'smooth' });
    }
  }, [displayedHistory]);

  const handlePausePlay = () => { // For pause/play button
    setIsPlaying(prevIsPlaying => !prevIsPlaying);
  };

  const handleStopRestart = () => { // For stop/restart button
    setIsStopped(prevIsStopped => {
      if (prevIsStopped) {
        setDisplayedHistory([]); // Clear history on restart
        setIsPlaying(true); // Start playing on restart
      } else {
        setIsPlaying(false); // Pause before stopping
      }
      return !prevIsStopped;
    });
  };


  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
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
        {error && <div className="error-message">{error}</div>}
        {result && <div className="result-message">{result}</div>}
        {orderOfBattle && <div className="order-of-battle">{orderOfBattle}</div>}
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