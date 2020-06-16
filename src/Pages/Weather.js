/* eslint-disable react-hooks/exhaustive-deps */
import React, { useContext, useEffect } from 'react';
import { AppContext } from '../Context/AppContext';
import WeatherTemplate from '../Components/Templates/WeatherTemplate';
import './style/Weather.css';

const Weather = () => {
  const context = useContext(AppContext);
  const { weatherState, fetchWeather, locationState } = context;

  console.log(locationState);
  console.log(weatherState);

  useEffect(() => {
    if (locationState.nx !== 1) fetchWeather(locationState.nx, locationState.ny);
  }, [locationState]);

  return (
    <div className="WeatherPage">
      <WeatherTemplate />
    </div>
  );
};

export default React.memo(Weather);
