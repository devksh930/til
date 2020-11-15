import React from 'react';
import MainRouter from './Router/MainRouter';
import { AppContextProvider } from './Context/AppContext';

function App() {
  return (
    <AppContextProvider>
      <MainRouter />
    </AppContextProvider>
  );
}

export default App;
