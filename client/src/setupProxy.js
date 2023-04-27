const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    [
      '/answer',
      '/boards',
      '/process_login',
      '/questions',
      '/members',
      '/test',
      '/v11',
    ],
    createProxyMiddleware({
      target:
        'http://ec2-13-124-206-153.ap-northeast-2.compute.amazonaws.com:8080',
      changeOrigin: true,
    })
  );
};
