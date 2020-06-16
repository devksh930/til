import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSun, faCloud, faCloudRain, faSnowflake, faCloudShowersHeavy } from '@fortawesome/free-solid-svg-icons';
import { faQuestionCircle } from '@fortawesome/free-regular-svg-icons';

const WeatherIcon = ({ SKY, PTY, size }) => {
  const skyState = SKY ? +SKY.value : 0;
  const ptyState = PTY ? +PTY.value : 0;

  let icon = faQuestionCircle;

  if (ptyState) {
    if (ptyState === 1) icon = faCloudRain;
    else if (ptyState === 2 || skyState === 3) icon = faSnowflake;
    else icon = faCloudShowersHeavy;
  } else if (skyState === 0) icon = faQuestionCircle;
  else if (skyState === 1) icon = faSun;
  else icon = faCloud;

  return <FontAwesomeIcon icon={icon} style={{ color: '#F8F8FF', fontSize: size }} />;
};

export default WeatherIcon;
