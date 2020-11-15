const { createProxyMiddleware } = require('http-proxy-middleware');

// src/setupProxy.js
module.exports = (app) => {
  app.use(
    createProxyMiddleware('/map-reversegeocode/', {
      target: 'https://naveropenapi.apigw.ntruss.com/',
      changeOrigin: true,
    }),
  );
  app.use(
    createProxyMiddleware('/getVilageFcst', {
      target: 'http://apis.data.go.kr/1360000/VilageFcstInfoService/',
      changeOrigin: true,
    }),
  );
};
