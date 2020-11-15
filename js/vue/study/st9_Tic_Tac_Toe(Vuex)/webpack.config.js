const VueloaderPlugin = require('vue-loader/lib/plugin'); //node moudule
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
            use: 'vue-loader',
        },{
            test:/\.css$/,
            use:[
                'vue-style-loader',
                'css-loader',
            ]
        }], // vue 는 js 파일이 아니라서 에러가난다 vue파일을 실행하려면 vue loader가 필요하다.
    },
    plugins: [
        new VueloaderPlugin(),
    ],
    output: {
        filename: '[name].js',
        path: path.join(__dirname,'dist'),
        publicPath:'/dist',
    },
};