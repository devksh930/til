import axios from 'axios';

const clientID = 'momfgazv3c';
const clientSecret = '9CYPpQJLW5tatn5WHP7QjwfLj8DAVtGF8jAbr1eE';

const mapApi = {
  getLoc: (lati, longi) =>
    axios.get(`/map-reversegeocode/v2/gc?request=coordsToaddr&coords=${longi},${lati}&sourcecrs=epsg:4326&orders=addr&output=json`, {
      headers: {
        'X-NCP-APIGW-API-KEY-ID': clientID,
        'X-NCP-APIGW-API-KEY': clientSecret,
      },
    }),
};

export default mapApi;
