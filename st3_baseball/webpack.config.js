const VueloaderPlugin = require('vue-loader/lib/plugin');
const path = require('path');

module.exports ={
    mode: 'development',
    devtool: 'eval',
    resolve: {
        extensions: ['.js','.vue']
    },
    entry: {
        app: path.join(__dirname,'main.js'),
    }, //app 하나로 합쳐질 파일이름이 된다.
    module: {
        rules:[{
            test: /\.vue$/,
            loader: 'vue-loader',
        }], // vue 는 js 파일이 아니라서 에러가난다 vue를 실행하려
    },
    plugins: [
        new VueloaderPlugin(),
    ],
    output: {
        filename: '[name].js',
        path: path.join(__dirname,'dist'),
    },
};