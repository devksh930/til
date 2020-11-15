//함수 호출시 다른함수를 파라미터로 전달
function add(a, b, callback) {
    var result = a + b;
    callback(result);

}

add(10, 10, function (result) {
    {
        console.log('파라미터로 전될된 콜백함수 호출 ');
        console.log('더하가 10,10의 결과 %d', result);
    }
});


//함수안에서 값을 반환할때 새로운 함수를 만들어 반환
function add(a, b, callback) {
    var result = a + b;
    callback(result);

    var count = 0;
    var history = function () {
        count++;
        return count + ':' + a + '+' + b + '=' + result;
    };
    return history;
}

var add_history = add(10, 10, function (result) {
    console.log('파라미터로 전달된 콜백 함수 호출');
    console.log('더하기 10,10 의 결과 %d', result);
});
for (i = 0; i < 10; i++) {
    console.log('결과값으로 받은 함수 실행' + add_history());
}