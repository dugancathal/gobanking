var helpers = require('./helpers');
var ProvidePlugin = require('webpack/lib/ProvidePlugin');
var DefinePlugin = require('webpack/lib/DefinePlugin');
const ENV = process.env.ENV = process.env.NODE_ENV = 'test';

module.exports = {
    devtool: 'source-map',
    resolve: {
        // When a module is defined, it might actually exist with the following extensions
        extensions: ['', '.ts', '.js'],
        root: helpers.root('app')
    },

    // Compile files according to the below
    module: {
        loaders: [
            {
                test: /\.ts$/,
                loader: 'ts-loader',
                query: {
                    "compilerOptions": {
                        "removeComments": true
                    }
                }
            }
        ]
    },

    plugins: [
        new DefinePlugin({'ENV': JSON.stringify(ENV), 'HMR': false})
    ],

    // Node configuration
    node: {
        global: 'window',
        process: false,
        crypto: 'empty',
        module: false,
        clearImmediate: false,
        setImmediate: false
    }

};