var webpack = require('webpack');
module.exports = {
    'cheap-source-maps': true,
    entry: './app/main.ts',
    devtool: 'source-map',
    output: {
        filename: 'dist/bundle.js'
    },
    resolve: {
        extensions: ['', '.webpack.js', '.web.js', '.ts', '.js']
    },
    module: {
        loaders: [
            {test: /\.ts$/, loader: 'ts-loader'}
        ]
    }
};
