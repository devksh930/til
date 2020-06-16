/* eslint-disable implicit-arrow-linebreak */
import axios from 'axios';

const key = 'serviceKey=ZybtbxqWembBr7iOGr%2BitD88U9KStnf%2Fs0BJmKU%2BnBycJUd634Izz31eQJhsQiM%2FnLHb3I%2FoAdlMnFp8zNh31Q%3D%3D';

const needParams = 'pageNo=1&numOfRows=300&dataType=JSON&base_time=0500';

const weatherApi = {
  getWeather: (today, nx, ny) => axios.get(`/getVilageFcst?${key}&${needParams}&base_date=${today}&nx=${nx}&ny=${ny}`),
};

export default weatherApi;
