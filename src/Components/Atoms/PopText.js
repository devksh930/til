import React from 'react';
import './style/TimeText.css';

const TimeText = ({ percentage }) => {
  return <span className="timeText">{percentage}%</span>;
};

export default TimeText;
