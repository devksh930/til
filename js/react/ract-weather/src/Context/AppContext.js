import React, { createContext } from 'react';
import useWeatherState from '../Hook/useWeatherState';
import useLocationState from '../Hook/useLocationState';

const AppContext = createContext(null);

const AppContextProvider = ({ children }) => {
  const [locationState, fetchLocation] = useLocationState();

  const [weatherState, fetchWeather] = useWeatherState();

  const data = {
    weatherState,
    fetchWeather,

    locationState,
    fetchLocation,
  };

  return <AppContext.Provider value={data}>{children}</AppContext.Provider>;
};

export { AppContext, AppContextProvider };
