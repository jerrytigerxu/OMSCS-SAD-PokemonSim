// FILE: SetRemoveSeed.js
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import './SetRemoveSeed.css'; // Import the CSS file for styling

const SetRemoveSeed = ({ onSeedChange }) => {
  const [seed, setSeed] = useState('');
  const [error, setError] = useState(null);

  const handleChange = (event) => {
    const value = event.target.value;
    if (/^\d*$/.test(value)) { // Ensure only integers are allowed
      setSeed(value);
    }
  };

  const handleRemoveSeed = async () => {
    setSeed('');
  };

  const handleSetSeed = async () => {
    setSeed(seed);
    try {
      const response = await fetch('http://localhost:8080/api/commands/setSeed', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ seed: parseInt(seed, 10) }),
      });
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      console.log('Seed set successfully:', data);
    } catch (error) {
      setError(error.message);
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
    </div>
  );
};

SetRemoveSeed.propTypes = {
  onSeedChange: PropTypes.func.isRequired,
};

export default SetRemoveSeed;