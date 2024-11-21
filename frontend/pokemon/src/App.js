import './App.css';
import React, { useState } from 'react';
import GetAllPokemon from './displayInfo/getAllPokemon';
import DisplayInfo from './displayInfo/displayInfo';

function App() {
  const [selectedPokemon, setSelectedPokemon] = useState('');
  return (
    <div className="App">
      <GetAllPokemon onSelect={setSelectedPokemon} />
      <DisplayInfo name={selectedPokemon} />
    </div>
  );
}

export default App;
