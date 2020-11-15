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

    var filename = './img/img.jpg';
    var infile = fs.createReadStream(filename, {flags: 'r'});
    var filelength = 0;
    var curlength = 0;

    fs.stat(filename, function (err, stats) {
        filelength = stats.size;
    });

    res.writeHead(200, {"Contents-Type": "image/jpeg"});

    infile.on('readable', function () {
        var chunk;
        while (null !== (chunk = infile.read())) {
            console.log("읽어 들인 데이터 크기 : %d 바이트", chunk.length);
            curlength += chunk.length;
            res.write(chunk, 'utf8', function (err) {
                console.log('파일 부분 쓰기 완료 :%d , 파일크기: %d', curlength, filelength);
                if (curlength >= filelength) {
                    res.end();
                }

            });
        }
    });
});

