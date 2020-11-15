// 사용자 정의 이벤트

process.on('tick', function (count) {
    console.log('tick 이벤트 발생함 :%s',count);
});

setTimeout(function () {
    console.log('2초 후에 tick 이벤트 전달 시도함. ');

    process.emit('tick','2');
    console.log('test');
},5000);