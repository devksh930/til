//http fileoutput

var http = require('http');
var fs = require('fs');
var server = http.createServer();

var host = '127.0.0.1';
var port = 3000;
server.listen(port, host, function () {
    console.log('웹 서버가 시작되었습니다 : %d', port);

});

server.on('request', function (req, res) {
    console.log('클라이언트요청이 들어옴');

    var filename = "./img/img.jpg";
    fs.readFile(filename, function (err, data) {
        res.writeHead(200, {"Content-Type": "image/jpeg"});
        res.write(data);
        res.end();

    })
});