/* eslint-disable react-hooks/exhaustive-deps */
import React from 'react';
import NowWeather from '../Organisms/NowWeather';
import ToUltraBtn from '../Atoms/ToUltraBtn';

const WeatherTemplate = () => {
  return (
    <div>
      <NowWeather />
      <ToUltraBtn />
      {/* <WeatherSubRouter /> */}
    </div>
  );
};

export default React.memo(WeatherTemplate);
