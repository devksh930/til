var express = require('express')
    , http = require('http')
    , path = require('path');

var bodyParser = require('body-parser')
    , static = require('serve-static');

var app = express();
var router = express.Router();

router.route('/process/login').post(function (req, res) {
    console.log('/process/login 처리함.');

    var paramId = req.body.id || req.query.id;
    var paramPassword = req.body.pw || req.query.pw;

    res.writeHead('200', {'Content-Type': 'text/html; charset=utf8'});
    res.write('<h1>Express 서버에서 응답한 결과입니다.</h1>');
    res.write('<div><p>Param id : ' + paramId + '</p></div>');
    res.write('<div><p>Param pw : ' + paramPassword + '</p></div>');
    res.write("<br><br><a href='./login.html'>로그인 페이지로 돌아가기</a>");
    res.end();
});

app.use('/',router);
// app.use(function (req, res, next) {
//     console.log('첫 번째 미들웨어에서 요청을 처리함');
//
//     res.writeHead('200', {'Content-Type': 'text/html;charset=utf8'});
//     res.end('<h1>Express 서버에서 응답한 결과 입니다.</h1>');
//
//     next();
http.createServer(app).listen(3000, function () {
    console.log('Express 서버가 3000번 포트에서 시작된.');
});
app.set('port', process.env.PORT || 3000);

//body-parser를 사용해 app/x-www-form-urlencodded

app.use(bodyParser.urlencoded({extended: false}));

//body-parser를 사용해 app/json 파싱

app.use(bodyParser.json());

app.use(static(path.join(__dirname, 'public')));

//미들웨어에서 파라미터 확인
