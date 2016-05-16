module.exports = function(config) {
    config.set({
        basePath: '.',
        frameworks: ['jasmine'],
        browsers: ['Chrome'],
        files: ['spec/tests.webpack.js'],
        preprocessors: {'spec/*': ['webpack']},
        webpack: {
            module:{
                loaders:[
                    { test: /\.js/, exclude: /node_modules/, loader: 'babel-loader'}
                ]
            }
        }
    });
};