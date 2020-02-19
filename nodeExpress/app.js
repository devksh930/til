var express = require('express')
    ,
    http = require('http');

var app = express();

//기본 포트 app객체에 속성으로 설정 process.env객체에 PORT속성이 있으면 그속성을 사용하고 아닐경우 3000포트 사용
app.set('port', process.env.PORT || 3000);

//express 서버 시작
http.createServer(app).listen(app.get('port'), function () {
    console.log("익스프레스 서버를 시작했다." + app.get('port'));
});