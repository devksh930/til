/* eslint-disable react-hooks/exhaustive-deps */
import { useReducer, useEffect } from 'react';
import { locationInit, locationReducer } from '../Reducer/locationReducer';

import { dfs_xy_conv } from '../Util/util';

import mapApi from '../API/mapApi';

const useLocation = () => {
  const [state, dispatch] = useReducer(locationReducer, locationInit);

  const getPosition = (options) => {
    return new Promise((resolve, reject) => {
      navigator.geolocation.getCurrentPosition(resolve, reject, options);
    });
  };

  const fetchLoaction = async () => {
    try {
      const nowLoaction = await getPosition();
      const { latitude, longitude } = nowLoaction.coords;

      const nxny = dfs_xy_conv('toXY', latitude, longitude);

      const resAddr = await mapApi.getLoc(latitude, longitude);

      if (resAddr.status === 200) {
        const addr = resAddr.data.results[0].region.area2.name + ' ' + resAddr.data.results[0].region.area3.name;
        dispatch({ type: 'SUCCESS', addr, longitude, latitude, nxny });
      } else {
        dispatch({ type: 'ERROR', message: resAddr.statusText });
      }
    } catch (error) {
      dispatch({ type: 'ERROR', message: error.message });
    }
  };

  useEffect(() => {
    fetchLoaction();
  }, []);

  return [state, fetchLoaction];
};

export default useLocation;
