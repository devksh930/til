import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import Weather from '../Pages/Weather';
import Weather2 from '../Pages/Weather2';
// import Dust from '../Pages/Dust';

const MainRouter = () => {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={Weather2} />
        <Route path="/ultrafcst" component={Weather} />
        <Route
          render={({ location }) => (
            <div>
              <h2>이 페이지는 존재하지 않습니다:</h2>
              <p>{location.pathname}</p>
            </div>
          )}
        />
      </Switch>
    </BrowserRouter>
  );
};

export default MainRouter;
