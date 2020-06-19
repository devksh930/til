import React from 'react';
import { Link } from 'react-router-dom';
import './style/ToUltraBtn.css';

const ToUltraBtn = () => {
  return (
    <Link to="/ultrafcst">
      <button className="toUltra" type="button">
        상세보기
      </button>
    </Link>
  );
};

export default ToUltraBtn;
