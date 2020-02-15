//module exports
var calc = require('./module/calc');
console.log('모듈로 분리한 후 calc.add 호출 %d', calc.add(10, 10));

var calc2 = require('./module/calc2');
console.log('모듈분리 calc2.add 실행 %d',calc2.add(10,10));