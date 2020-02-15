
//console log, dir, time 객체
var result = 0;

console.time('duration_sum');

for (var i = 1; i <= 1000; i++) {
    result += i;
}
console.timeEnd('duration_sum');
console.log('1 부터 1000 까지 %d,', result);

console.log('실핸한 파일이름: %s', __filename);
console.log('실행한 패스이름 :%s ', __dirname);

var Person = {name: "소녀시대", age: 20};
console.dir(Person);