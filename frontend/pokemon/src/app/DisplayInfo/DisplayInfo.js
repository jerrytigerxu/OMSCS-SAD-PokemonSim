// FILE: DisplayInfo.js
import React, { useState, useEffect } from 'react';
import PropTypes from 'prop-types';

const DisplayInfo = ({ name }) => {
  const [pokemonInfo, setPokemonInfo] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  function parsePokemonResponse(responseText) {
    const lines = responseText.split('\n');
    const pokemon = {
      name: '',
      hp: 0,
      attackSkills: [],
      defenseSkills: []
    };

    lines.forEach(line => {
      if (line.startsWith('Pokemon:')) {
        const parts = line.split(' ');
        pokemon.name = parts[1];
        pokemon.hp = parseInt(parts[3]);
      } else if (line.startsWith('Name:') && line.includes('Damage:')) {
        const parts = line.split(' ');
        let stringsInName = parts.length - 3;
        pokemon.attackSkills.push({
          name: parts.slice(1, stringsInName + 1).join(' '),
          damage: parseInt(parts[parts.length - 1])
        });
      } else if (line.startsWith('Name:') && line.includes('Defense:')) {
        const parts = line.split(' ');
        let stringsInName = parts.length - 3;
        pokemon.defenseSkills.push({
          name: parts.slice(1, stringsInName + 1).join(' '),
          defense: parseInt(parts[parts.length - 1])
        });
      }
    });

    return pokemon;
  }

  useEffect(() => {
    const fetchPokemonInfo = async () => {
      if (name) {
        try {
          const response = await fetch(`http://localhost:8080/api/commands/displayInfo/${name}`);
          const text = await response.text();
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          const data = parsePokemonResponse(text);
          setPokemonInfo(data);
        } catch (error) {
          setError(error);
        } finally {
          setLoading(false);
        }
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
    <div className="pokemon-info">
      <h3>Pokemon: {pokemonInfo.name} has {pokemonInfo.hp} hp</h3>
      <h4>Attack Skills:</h4>
      <table className="skills-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Damage</th>
          </tr>
        </thead>
        <tbody>
          {pokemonInfo.attackSkills.map((skill, index) => (
            <tr key={index}>
              <td>{skill.name}</td>
              <td>{skill.damage}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <h4>Defense Skills:</h4>
      <table className="skills-table">
        <thead>
          <tr>
            <th>Name</th>
            <th>Defense</th>
          </tr>
        </thead>
        <tbody>
          {pokemonInfo.defenseSkills.map((skill, index) => (
            <tr key={index}>
              <td>{skill.name}</td>
              <td>{skill.defense}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

DisplayInfo.propTypes = {
  name: PropTypes.string.isRequired,
};

export default DisplayInfo;