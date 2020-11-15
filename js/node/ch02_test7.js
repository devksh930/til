var os = require('os');

console.log('HOSTNAME:%s', os.hostname());
console.log('SYSTEM MEMORY: %d/%d', os.freemem(),os.totalmem());
console.log('SYSTEM CPU INFO\n');
console.dir(os.cpus());
console.log('SYSTEM NETWORK INTERFACE');
console.dir(os.networkInterfaces());