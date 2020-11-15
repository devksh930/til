var http = require('http');

var server = http.createServer();

var host = '127.0.0.1';
var port = 3000;
server.listen(port, host, function () {
    console.log('웹 서버가 시작되었습니다 : %d', port);

});