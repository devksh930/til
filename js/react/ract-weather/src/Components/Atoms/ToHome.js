import React from 'react';
import { Link } from 'react-router-dom';
import './style/ToUltraBtn.css';

const ToUltraBtn = () => {
  return (
    <Link to="/">
      <button className="toUltra" type="button">
        현재날씨
      </button>
    </Link>
  );
};

export default ToUltraBtn;
