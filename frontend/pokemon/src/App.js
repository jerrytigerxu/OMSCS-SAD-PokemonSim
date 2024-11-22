import './App.css';
import React, { useState } from 'react';
import GetAllPokemon from './app/DisplayInfo/GetAllPokemon';
import DisplayInfo from './app/DisplayInfo/DisplayInfo';
import Base from './app/Base/Base';
import Battle from './app/Battle/Battle';
import { useEffect } from 'react';
import SetRemoveSeed from './app/SetSeed/SetRemoveSeed';

function App() {
  const [selectedPokemon, setSelectedPokemon] = useState('');
  const [selectedOption, setSelectedOption] = useState('');
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

  const handleOptionSelect = (option) => {
    setSelectedOption(option);
    console.log(`Selected option: ${option}`);
  };

  const renderComponent = () => {
    switch (selectedOption) {
      case 'Display Pokemon':
        return (
          <>
            <GetAllPokemon onSelect={setSelectedPokemon} />
            <DisplayInfo name={selectedPokemon} pokemonList={pokemonList} />
          </>
        );
      case 'Battle':
        return <Battle />;
      case 'Tournament':
        return <div>Tournament Component</div>;
      case 'Set Seed':
        return (
          <SetRemoveSeed />
        );
      default:
        return null;
    }
  };


  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  return (
    <div className="App">
      <div className="base">
        <button onClick={() => handleOptionSelect('Display Pokemon')}>Display Pokemon</button>
        <button onClick={() => handleOptionSelect('Battle')}>Battle</button>
        <button onClick={() => handleOptionSelect('Tournament')}>Tournament</button>
        <button onClick={() => handleOptionSelect('Set Seed')}>Set/Remove Seed</button>
      </div>
      <div className="component-container">
        {renderComponent()}
      </div>
    </div>
  );
}
export default App;
