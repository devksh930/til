import React from 'react';
import { Route, Switch } from 'react-router-dom';
import WeatherSubDgree from '../Components/Molecules/WeatherSubDegree';
import WeatherSubPop from '../Components/Molecules/WeatherSubPop';

const WeatherSubRouter = () => {
  return (
    <Switch>
      <Route exact path="/" render={WeatherSubDgree} />
      <Route exact path="/pop" component={WeatherSubPop} />
    </Switch>
  );
};

export default WeatherSubRouter;
