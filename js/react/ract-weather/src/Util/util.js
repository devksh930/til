/* eslint-disable operator-linebreak */
/* eslint-disable object-curly-newline */
/* eslint-disable no-restricted-properties */
/* eslint-disable dot-notation */
/* eslint-disable camelcase */
/* eslint-disable prefer-template */
const getToday = () => {
  const today = new Date();

  const year = today.getFullYear().toString();
  let month = (today.getMonth() + 1).toString();
  let date = today.getDate().toString();

  month = month.length - 1 ? month : '0' + month;
  date = date.length - 1 ? date : '0' + date;

  return year + month + date;
};

const getYesterday = () => {
  const today = new Date();

  const yesterday = new Date(today.setDate(+today.getDate() - 1));

  const year = yesterday.getFullYear().toString();
  let month = (yesterday.getMonth() + 1).toString();
  let date = yesterday.getDate().toString();

  month = month.length - 1 ? month : '0' + month;
  date = date.length - 1 ? date : '0' + date;

  return year + month + date;
};

const getTime = () => {
  const today = new Date();

  let time = today.getHours();

  time = time.length - 1 ? time : '0' + time;

  return time + '00';
};

function dfs_xy_conv(code, v1, v2) {
  const RE = 6371.00877; // 지구 반경(km)
  const GRID = 5.0; // 격자 간격(km)
  const SLAT1 = 30.0; // 투영 위도1(degree)
  const SLAT2 = 60.0; // 투영 위도2(degree)
  const OLON = 126.0; // 기준점 경도(degree)
  const OLAT = 38.0; // 기준점 위도(degree)
  const XO = 43; // 기준점 X좌표(GRID)
  const YO = 136; // 기1준점 Y좌표(GRID)

  const DEGRAD = Math.PI / 180.0;
  const RADDEG = 180.0 / Math.PI;

  const re = RE / GRID;
  const slat1 = SLAT1 * DEGRAD;
  const slat2 = SLAT2 * DEGRAD;
  const olon = OLON * DEGRAD;
  const olat = OLAT * DEGRAD;

  let theta;
  let ra;

  let sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
  sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
  let sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
  sf = (Math.pow(sf, sn) * Math.cos(slat1)) / sn;
  let ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
  ro = (re * sf) / Math.pow(ro, sn);
  const rs = {};
  if (code === 'toXY') {
    rs['lat'] = v1;
    rs['lng'] = v2;
    ra = Math.tan(Math.PI * 0.25 + v1 * DEGRAD * 0.5);
    ra = (re * sf) / Math.pow(ra, sn);
    theta = v2 * DEGRAD - olon;
    if (theta > Math.PI) theta -= 2.0 * Math.PI;
    if (theta < -Math.PI) theta += 2.0 * Math.PI;
    theta *= sn;
    rs['x'] = Math.floor(ra * Math.sin(theta) + XO + 0.5);
    rs['y'] = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);
  } else {
    rs['x'] = v1;
    rs['y'] = v2;
    const xn = v1 - XO;
    const yn = ro - v2 + YO;
    ra = Math.sqrt(xn * xn + yn * yn);
    if (sn < 0.0) ra = -ra;
    let alat = Math.pow((re * sf) / ra, 1.0 / sn);
    alat = 2.0 * Math.atan(alat) - Math.PI * 0.5;

    if (Math.abs(xn) <= 0.0) {
      theta = 0.0;
    } else if (Math.abs(yn) <= 0.0) {
      theta = Math.PI * 0.5;
      if (xn < 0.0) theta = -theta;
    } else theta = Math.atan2(xn, yn);
    const alon = theta / sn + olon;
    rs['lat'] = alat * RADDEG;
    rs['lng'] = alon * RADDEG;
  }
  return rs;
}

export { getToday, getYesterday, getTime, dfs_xy_conv };
