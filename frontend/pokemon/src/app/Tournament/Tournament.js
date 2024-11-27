import React, { useState, useEffect } from 'react';
import './Tournament.css';

const Tournament = () => {
    const [pokemonList, setPokemonList] = useState([]);
    const [selectedPokemon, setSelectedPokemon] = useState([]);
    const [tournamentData, setTournamentData] = useState(null);
    const [selectedBattleIndex, setSelectedBattleIndex] = useState(0);

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    const handlePokemonSelect = (event) => {
        const selectedOptions = Array.from(event.target.selectedOptions, (option) => option.value);
        setSelectedPokemon(selectedOptions);
    };

    const startTournament = async () => {
        if (selectedPokemon.length < 2) {
            alert('Please select at least two Pokemon for the tournament.');
            return;
        }

        try {
            const response = await fetch(
                `http://localhost:8080/api/commands/tournament/${selectedPokemon.join(",")}`,
              );
            if (!response.ok) {
                if (response.status === 400) {
                  const errorData = JSON.stringify(await response.text());
                  throw new Error(errorData || 'Bad Request');
                }
            }
            const data = await response.json();
            console.log("Tournament data:", data);

            const lastBattle = data.battleResultList[data.battleResultList.length - 1];
            const tournamentWinner = lastBattle.winnerPokemon;

            setTournamentData({ ...data, winnerPokemon: tournamentWinner });
        } catch (error) {
            console.log(error.message);
            setError(error.message);
        }
    }


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

    return (
        <div>
            <select multiple={true} onChange={handlePokemonSelect}> {/* Multi-select option */}
                {pokemonList.map((pokemon, index) => (
                    <option key={index} value={pokemon}>
                        {pokemon}
                    </option>
                ))}
            </select>
            <div>
                <h3>Selected Pokemon:</h3>
                <ul>
                    {selectedPokemon.map((pokemon, index) => (
                        <li key={index}>{pokemon}</li>
                    ))}
                </ul>
            </div>
            <button onClick={startTournament}>Start Tournament</button>

            {/* Conditionally render the dropdown and battle details */}
            {tournamentData && (
                <>
                    <div className="tournament-winner">
                        Tournament Winner: {tournamentData.winnerPokemon}
                    </div>
                    <hr />
                    <select value={selectedBattleIndex} onChange={(e) => setSelectedBattleIndex(parseInt(e.target.value, 10))}>
                        {tournamentData.battleResultList.map((battle, index) => (
                            <option key={index} value={index}>
                                Battle {index + 1}: {battle.winnerPokemon} vs {battle.loserPokemon}
                            </option>
                        ))}
                    </select>

                    <div className="battle-details">
                        <h3>Battle {selectedBattleIndex + 1} Details:</h3>
                        <p>Winner: {tournamentData.battleResultList[selectedBattleIndex].winnerPokemon}</p>
                        <p>Loser: {tournamentData.battleResultList[selectedBattleIndex].loserPokemon}</p>

                        <h4>Battle History:</h4>
                        <ul>
                            {tournamentData.battleResultList[selectedBattleIndex].orderOfBattle.map((event, index) => (
                                <li key={index}>{event}</li>
                            ))}  
                        </ul>
                    </div>
                </>
            )}
        </div>
    );

};


export default Tournament;