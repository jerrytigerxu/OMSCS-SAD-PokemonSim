// FILE: SetRemoveSeed.js
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import './SetRemoveSeed.css'; // Import the CSS file for styling

const SetRemoveSeed = ({ onSeedChange }) => {
  const [seed, setSeed] = useState('');
  const [message, setMessage] = useState(null);
  const [error, setError] = useState(null);
  //const [result, setResult] = useState('');

  const handleChange = (event) => {
    const value = event.target.value;
    if (/^\d*$/.test(value)) { // Ensure only integers are allowed
      setSeed(value);
    }
  };

  const handleRemoveSeed = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/commands/removeSeed', {
        method: 'DELETE'
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      //const data = await response.json();
      //console.log('Seed removed successfully:', data);
      //console.log(seed)
      setMessage("Seed successfully removed");
      setSeed(""); // Clear the seed input field
      console.log(seed)

    } catch (error) {
      console.error(error.message);
      setError(error.message);
    }
  };

  const handleSetSeed = async () => {
    setSeed(seed);

    if (!seed) {  // Check if the seed input is empty
      alert("Please enter a seed value.");
      return;
    }
    
    try {
      const response = await fetch('http://localhost:8080/api/commands/setSeed', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ seed: parseInt(seed, 10) }),
      });
      if (!response.ok) {
        if(response.status === 400) {
          const errorData = JSON.stringify(await response.text());
          throw new Error(errorData || 'Bad Request');
        }
        throw new Error('Network response was not ok');
      }
      setMessage(`Seed "${seed}" successfully set.`);
      setSeed("");
    } catch (error) {
      setError(error.message);
      //setResult('');
    }
  };

  return (
    <div className="set-seed">
      <h2>Set Seed</h2>
      <input
        type="text"
        value={seed}
        onChange={handleChange}
        placeholder="Enter seed"
        className="seed-input"
      />
      <button onClick={handleSetSeed} className="remove-seed-button">Set Seed</button>
      <button onClick={handleRemoveSeed} className="remove-seed-button">Remove Seed</button>
      {message && <div className="result-message">{message}</div>}
      {error && <div className="error-message">{error}</div>}
    </div>
  );
};

SetRemoveSeed.propTypes = {
  onSeedChange: PropTypes.func.isRequired,
};

export default SetRemoveSeed;