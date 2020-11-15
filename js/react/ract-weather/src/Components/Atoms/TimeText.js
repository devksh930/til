import React from 'react';
import './style/TimeText.css';

const TimeText = ({ time }) => {
  const stringTime = time.replace(/(.{2})/, '$1:');

  // const stringTime = time;
  return <span className="timeText">{stringTime}</span>;
};

export default TimeText;
