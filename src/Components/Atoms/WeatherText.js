import React, { useContext } from 'react';
import { AppContext } from '../../Context/AppContext';

const WeatherText = () => {
  const context = useContext(AppContext);
  const { weatherState } = context;

  const skyState = weatherState.SKY[0] ? +weatherState.SKY[0].value : 0;
  const ptyState = weatherState.PTY[0] ? +weatherState.PTY[0].value : 0;

  let text = '날씨 불러오는 중';

  if (ptyState) {
    if (ptyState === 1) text = '비';
    else if (ptyState === 2 || skyState === 3) text = '눈';
    else text = '소나기';
  } else if (skyState === 0) text = '날씨 불러오는 중';
  else if (skyState === 1) text = '맑음';
  else text = '구름 많음';

  return <strong style={{ color: 'white' }}>{text}</strong>;
};

export default WeatherText;
