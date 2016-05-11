module.exports = function(config) {
    config.set({
        basePath: './',
        frameworks: ['jasmine'],

        // the spec-bundle defines and includes libraries and spec files
        files: [
            { pattern: 'spec/config/spec-bundle.js', watched: false },
            { pattern: 'app/**/*.html', included: false, watched: false }
        ],
        proxies: {
            "/app/": "/base/app/",
            "/dist/": "/base/dist/"
        },
        preprocessors: { './spec/config/spec-bundle.js': ['webpack'] },

        webpackServer: { noInfo: true },
        webpack: require('./spec/config/webpack.test.js'),

        reporters: ['spec'],
        port: 9876,
        colors: true,

        // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
        logLevel: config.LOG_INFO,

        autoWatch: false,


        browsers: ['PhantomJS'],
        singleRun: false,

        concurrency: Infinity
    })
};
