import React, { useContext } from 'react';
import { AppContext } from '../../Context/AppContext';

const MinMaxDgreeText = () => {
  const context = useContext(AppContext);
  const { weatherState } = context;

  const dgreeMin = weatherState.TMN[0] ? +weatherState.TMN[0].value : 0;
  const dgreeMax = weatherState.TMX[0] ? +weatherState.TMX[0].value : 0;

  return (
    <strong style={{ color: 'white' }}>
      최고 {dgreeMax}도 / 최저 {dgreeMin}도
    </strong>
  );
};

export default MinMaxDgreeText;
