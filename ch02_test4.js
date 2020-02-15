var calc = {};
calc.add = function (a, b) {
    return a + b;
}

console.log('모듈 분리전 clac.add 함수 호출 결과 %d',calc.add(10,10));