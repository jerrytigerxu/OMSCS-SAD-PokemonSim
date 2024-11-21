// FILE: DisplayInfo.js
import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';

const DisplayInfo = ({ name }) => {
  const [pokemonInfo, setPokemonInfo] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchPokemonInfo = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/commands/displayInfo/${name}`);
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        const data = await response.json();
        setPokemonInfo(data);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchPokemonInfo();
  }, [name]);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  return (
    <div>
      <h1>{pokemonInfo}</h1>
    </div>
  );
};

DisplayInfo.propTypes = {
  name: PropTypes.string.isRequired,
};

export default DisplayInfo;