/*
 cookie-parser를 활용한 쿠키관리하기a
 */

var express = require('express')
    , http = require('http')
    , path = require('path');

var bodyParser = require('body-parser')
    , static = require('serve-static');

var app = express();
var router = express.Router();
var expressErrorHandler = require('express-error-handler');
var cookieParser = require('cookie-parser');
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
//cookie-parser
app.use(cookieParser());

router.route('/process/showCookie').get(function (req, res) {
    console.log('/process/showCookie 호출됨');
    res.send(req.cookie);
});
router.route('/process/setUserCookie').get(function (req, res) {
    console.log('/process/setUserCookie');

    res.cookie('users', {
        id: 'devksh930',
        name: '김성호',
        authorized: true
    });

    res.redirect('/process/showCookie');
});
app.use('/', router);

var errorHandler = expressErrorHandler({
    static: {
        '404': './public/404.html'
    }
});

app.use(expressErrorHandler.httpError(404));
app.use(errorHandler);

