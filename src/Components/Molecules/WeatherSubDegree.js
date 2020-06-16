/* eslint-disable react/no-array-index-key */
import React, { useContext } from 'react';
import { AppContext } from '../../Context/AppContext';
import WeatherIcon from '../Atoms/WeatherIcon';
import DegreeText from '../Atoms/DegreeText';
import TimeText from '../Atoms/TimeText';

import './style/WeatherSub.css';

const WeatherSubDgree = () => {
  const context = useContext(AppContext);
  const { weatherState } = context;

  return (
    <ul className="subContainer">
      {weatherState.T3H.map((T3H, i) => {
        if (i > 5) return null;
        return (
          <li className="subLi" key={i}>
            <TimeText time={weatherState.SKY[i].time} />
            <WeatherIcon SKY={weatherState.SKY[i]} PTY={weatherState.PTY[i]} size="2.7rem" />
            <DegreeText T3H={T3H} size="0.9rem" />
          </li>
        );
      })}
    </ul>
  );
};

export default WeatherSubDgree;
