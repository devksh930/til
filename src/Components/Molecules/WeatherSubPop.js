/* eslint-disable react/no-array-index-key */
import React, { useContext } from 'react';
import { AppContext } from '../../Context/AppContext';
import PopIcon from '../Atoms/PopIcon';
import PopText from '../Atoms/PopText';
import TimeText from '../Atoms/TimeText';

import './style/WeatherSub.css';

const WeatherSubDgree = () => {
  const context = useContext(AppContext);
  const { weatherState } = context;

  return (
    <ul className="subContainer">
      {weatherState.POP.map((POP, i) => {
        if (i > 5) return null;
        return (
          <li className="subLi" key={i}>
            <TimeText time={weatherState.SKY[i].time} />
            <PopIcon POP={weatherState.POP[i]} size="2.5rem" />
            <PopText percentage={POP.value} />
          </li>
        );
      })}
    </ul>
  );
};

export default WeatherSubDgree;
