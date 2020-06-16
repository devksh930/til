import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTint, faTintSlash } from '@fortawesome/free-solid-svg-icons';

const PopIcon = ({ POP, size }) => {
  const popState = POP ? +POP.value : 0;

  let icon = faTint;

  if (!popState) icon = faTintSlash;

  return <FontAwesomeIcon icon={icon} style={{ color: '#F8F8FF', fontSize: size }} />;
};

export default PopIcon;
