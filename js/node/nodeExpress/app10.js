/*
token 처리
 */

var express = require('express')
    , http = require('http')
    , path = require('path');

var bodyParser = require('body-parser')
    , static = require('serve-static');

var app = express();
var router = express.Router();
var expressErrorHandler = require('express-error-handler');
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

router.route('/process/users/:id').get(function (req, res) {
    console.log('/process/users/:id 처리함 ');

    var paramId = req.params.id;

    console.log('/process/users 와 토큰 %s 를 이용해 처리함',paramId);

    res.writeHead('200', {'Content-Type': 'text/html; charset=utf-8'});
    res.write('<h1>Express 서버에서 응답한 결과입니다</h1>');
    res.write('<div><p>Param id: ' + paramId + '</p></div>');
    res.end();
});

app.use('/',router);

var errorHandler = expressErrorHandler({
    static:{
        '404': './public/404.html'
    }
});

app.use(expressErrorHandler.httpError(404));
app.use(errorHandler);
