//process 객체

console.log('argv 속성 파라미터 갯수:' + process.argv.length);
console.dir(process.argv);

if (process.argv.length > 2) {
    console.log('세번째 파라미터의 값: %s', process.argv[2]);
}

process.argv.forEach(function (item, index) {
    console.log(index +':',item);

})

console.dir(process.env);
console.log('OS환경변수의 값: ' +process.env[OS]);