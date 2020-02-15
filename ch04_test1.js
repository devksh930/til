var url = require('url');
var queryString = require('querystring');

//주소 문자열 url 객체로 만들
var curURL = url.parse('https://m.search.naver.com/search.naver?query=steve+jobs&where=m&sm=mtp_hty');

// url객체를 문자열로 만들기

var curStr = url.format(curURL);

console.log('주소문자열: %s',curStr);
console.dir(curURL);

//요청파라미터 구분하기

var param= queryString.parse(curURL.query);

console.log('요청파라미터중 query 값 %s',param.query);
console.log('원본요청 파라미터 %s',queryString.stringify(param));