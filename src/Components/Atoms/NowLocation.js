import React, { useContext } from 'react';
import { AppContext } from '../../Context/AppContext';

const NowLocation = () => {
  const context = useContext(AppContext);
  const { locationState } = context;

  return <strong style={{ color: '#ffffff', fontSize: '2rem' }}>{locationState.addr}</strong>;
};

export default NowLocation;
