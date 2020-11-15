//event listener
/*
on(event,listener) 리스너추가
once(evnet,listener) 리스너를 추가하지만 실행한후 자동으로 리스너제거
removeListener(event,listener) 지정한 이벤트에 대한 리스너 제거
 */
process.on('exit', function () {
console.log('exit 이벤트 발생');
});

setTimeout(function () {
    console.log('2초후 종료됨');

    process.exit();
},2000);

