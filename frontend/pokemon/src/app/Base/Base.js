// FILE: Base.js
import React from 'react';

const Base = ({ onOptionSelect }) => {
  return (
    <div className="base">
      <button onClick={() => onOptionSelect('Display Pokemon')}>Display Pokemon</button>
      <button onClick={() => onOptionSelect('Battle')}>Battle</button>
      <button onClick={() => onOptionSelect('Tournament')}>Tournament</button>
      <button onClick={() => onOptionSelect('Set Seed')}>Set Seed</button>
      <button onClick={() => onOptionSelect('Remove Seed')}>Remove Seed</button>
    </div>
  );
};

export default Base;