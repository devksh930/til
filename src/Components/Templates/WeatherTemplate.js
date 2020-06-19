/* eslint-disable react-hooks/exhaustive-deps */
import React from 'react';
import NowWeather from '../Organisms/NowWeather';
import WeatherSubDgree from '../Molecules/WeatherSubDegree';
import WeatherSubPop from '../Molecules/WeatherSubPop';
import ToHome from '../Atoms/ToHome';
// import WeatherSubRouter from '../../Router/WeatherSubRouter';

const WeatherTemplate = () => {
  return (
    <div>
      <NowWeather />
      {/* <WeatherSubRouter /> */}
      <WeatherSubDgree />
      <WeatherSubPop />
      <ToHome />
    </div>
  );
};

export default React.memo(WeatherTemplate);
