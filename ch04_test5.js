var fs = require('fs');

//파일 sync io로 READ
var data = fs.readFileSync('./package.json', 'utf-8');
console.log(data);

//파일 ASYNC
fs.readFile('./package-lock.json', 'utf8', function (err, data) {
    console.log(data);

});