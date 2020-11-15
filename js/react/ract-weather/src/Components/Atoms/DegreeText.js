import React from 'react';

const DegreeText = ({ T3H, size }) => {
  const dgreeState = T3H ? +T3H.value : 0;

  return <strong style={{ color: '#F8F8FF', fontSize: size }}>{dgreeState}도</strong>;
};

export default DegreeText;
