/* eslint-disable max-len */
import React from 'react';
import { faQuestionCircle } from '@fortawesome/free-regular-svg-icons';
import { faSun, faCloud, faCloudRain, faSnowflake, faCloudShowersHeavy, faICursor } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

const QuestionCircle = () => <FontAwesomeIcon icon={faQuestionCircle} />;
const Sun = () => <FontAwesomeIcon icon={faSun} />;
const Cloud = () => <FontAwesomeIcon icon={faCloud} />;
const CloudRain = () => <FontAwesomeIcon icon={faCloudRain} />;
const Snowflake = () => <FontAwesomeIcon icon={faSnowflake} />;
const CloudShowersHeavy = () => <FontAwesomeIcon icon={faCloudShowersHeavy} />;
const ICursor = () => <FontAwesomeIcon icon={faICursor} />;

export { QuestionCircle, Sun, Cloud, CloudRain, Snowflake, CloudShowersHeavy, ICursor };
