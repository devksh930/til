/* eslint-disable implicit-arrow-linebreak */
import axios from 'axios';

const key = 'serviceKey=lJTDmCcwVvAdZrCl1KltSXYIelQuPm1MRsPIVqwtWAFoRP7dbGUYtA%2BCRT1L%2FVNpw2UG0vMvZoaC1mneGF0JRg%3D%3D';

const needParams = 'pageNo=1&numOfRows=300&dataType=JSON&base_time=0500';

const weatherApi = {
  getWeather: (today, nx, ny) => axios.get(`/getVilageFcst?${key}&${needParams}&base_date=${today}&nx=${nx}&ny=${ny}`),
};

export default weatherApi;
