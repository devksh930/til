/*
 session login
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
var expressSession = require('express-session');
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
app.use(expressSession({
    secret: 'my key',
    resave: true,
    saveUninitialized: true
}));


router.route('/process/product').get(function (req, res) {
    console.log('/process/product 호출됨');
    res.send(req.cookie);

    if (req.session.user) {
        res.redirect('/public/product.html');
    } else {
        res.redirect('/public/login.html');
    }
});

router.route('/process/login').post(function (req, res) {
    console.log('/process/login 호출됨');

    var paramId = req.body.id || req.query.id;
    var paramPassword = req.body.pw || req.query.pw;

    if (req.session.user) {
        //이미 로그인 된 상태
        console.log('이미 로그인되어 상품 페이지로 이동합니다.');
        res.redirect('public/product.html');
    } else {
        req.session.user = {
            id: paramId,
            name: '김성호',
            authorized: true
        };
        res.writeHead('200', {'Content-Type': 'text/html;charset=utf8'});
        res.write('<h1>Express 서버에서 응답한 결과입니다</h1>');
        res.write('<div><p>Param id: ' + paramId + '</p></div>');
        res.write('<div><p>Param password : ' + paramPassword + '</p></div>');
        res.write('<br><br><a href="/process/product">상품페이지로 이동하기</a>');
        res.end;
    }
});

//로구아웃 라우팅함수
router.route('/process/logout').get(function (req, res) {
    console.log('로그아웃 호출됨');

    if (req.session.user) {
        console.log('로그아웃 합니다');

        req.session.destroy(function (err) {
            if (err) {
                throw err;
            }

            console.log('세션을 삭제하고 로그아웃 하였습니다.');
            res.redirect('/public/login.html');
        });
    } else {
        //not logined
        console.log('아직 로그인이 되어있지 않습니다.');
        res.redirect('/public/login.html');
    }
});

router.route('/process/photo').post(upload.array('photo', 1), function (req, res) {
    console.log('/process/photo 호출됨');

    try {
        var files = req.files;
        console.dir('#====== 업로드된 첫번째 파일 정보 =====#');
        console.dir(req.files[0]);
        console.dir('#====#');

        //현재의 파일 정보를 저장할 변수 선언
        var originalname = '',
            filename = '',
            mimetype = '',
            size = 0;

        if (Array.isArray(files)) {
            //배열에 들어가 있는 경우 (설정에서 1개의 파일도 배열에 넣게함)
            console.log('배열에 들어가있는 파일 갯수: %d', files.length);

            for (var index = 0; index < files.length; index++) {
                originalname = files[index].originalname;
                filename = files[index].filename;
                mimetype = files[index].mimetype;
                size = files[index].size;

            }
        } else {
            //배열에 들어가 있지 않은 경우 (현재 설정에서는 해당없음)
            console.log("파일갯수 : 1");
            originalname = files[index].originalname;
            filename = files[index].filename;
            mimetype = files[index].mimetype;
            size = files[index].size;

        }
        console.log('현재 파일 정보 : ' + originalname + ',' + filename + ',' + mimetype +
            ',' + size
        );
        // 클라이언트에 응답 전송
        res.write('200', {'Content-Type': 'text/html;charset=utf8'});
        res.write('<h3>파일 업로드 성공</h3>');
        res.write('<hr/>');
        res.write('<p>원본 파일 이름: ' + originalname + '-> 저장파일명:' + filename + '</p>');
        res.write('<p>MIME TYPE:' + mimetype + '</p>');
        res.write('<p>파일크기:' + size + '</p>');
        res.end();
    } catch (err) {
        console.log(err.stack);
    }

});
app.use('/', router);


var errorHandler = expressErrorHandler({
    static: {
        '404': './public/404.html'
    }
});

app.use(expressErrorHandler.httpError(404));
app.use(errorHandler);

