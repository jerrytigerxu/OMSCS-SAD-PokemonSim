// FILE: dto/Pokemon.js
import PropTypes from 'prop-types';

const Pokemon = PropTypes.shape({
  name: PropTypes.string.isRequired,
  attackSkills: PropTypes.arrayOf(PropTypes.string).isRequired,
  defenseSkills: PropTypes.arrayOf(PropTypes.string).isRequired,
  hp: PropTypes.number.isRequired,
});

export default Pokemon;