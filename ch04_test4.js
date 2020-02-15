//계산기 객체 모듈로 만들기
var Calc = require('./module/calc3');

var calc = new Calc();
calc.emit('stop');

console.log(Calc.title + '에 stop 이벤트 전달');
