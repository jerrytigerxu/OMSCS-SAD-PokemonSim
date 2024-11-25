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
  const [error, setError] = useState(null);
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
    console.log('pokeon1: ' + pokemon1);
    console.log('pokemon2: ' + pokemon2);
    if (pokemon1.length !== 1 || pokemon2.length !== 1) {
      alert('Please select exactly two Pokemon to start the battle.');
      return;
    }
    console.log('Starting battle between ' + pokemon1 + ' and ' + pokemon2);

    // Added a fetch call to check that the seed is set before allowing battle to run
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
      setDisplayedHistory([]);
      setIsStopped(false); // Start in a running state
      setIsPlaying(true);
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
          <div className="battle-history-scroll">
            {displayedHistory.map((event, index) => (
              <pre key={index} className="battle-event">
                {event}
                <hr className="separator" />
              </pre>
            ))}
            <div ref={historyEndRef} />
          </div>
          <button onClick={handlePausePlay}>{isPlaying ? 'Pause' : 'Play'}</button>
          <button onClick={handleStopRestart}>{isStopped ? 'Restart' : 'Stop'}</button>
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