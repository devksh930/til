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
    console.log('클라이언트 요청이 들어왔습니다. ');

    var filename ='img/img.jpg';
    var infile =fs.createReadStream(filename,{flags:'r'});

    //파이프로 연결하여 알ㅇ서 처리하도록 설정
    infile.pipe(res);
});
