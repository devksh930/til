//프로토타입 오브젝트
function Person(name, age) {
    this.name = name;
    this.age = age;
}

Person.prototype.walk = function (speed) {
    console.log(speed + 'km 속도로 걸어갑니다.');
};

var person01 = new Person('소녀시대', 20);
var person02 = new Person('걸스데이', 22);

console.log(person01.name + '객체의 walk(10)호출');
person01.walk(10);

console.log(person02.name + '객체의 walk(10)호출');
person01.walk(10);