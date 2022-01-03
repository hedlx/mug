const path = require('path');
const MonacoWebpackPlugin = require('monaco-editor-webpack-plugin');

const isDev = process.env.NODE_ENV === 'development';
const BUILD_DIR = isDev
	? path.resolve(__dirname, 'resources', 'public', 'js')
	: path.resolve(__dirname, 'prod', 'js');
const APP_DIR = path.resolve(__dirname, 'src', 'js');

const config = {
	mode: 'production',
  entry: `${APP_DIR}/main.js`,
  module: {
		rules: [
			{
				test: /\.css$/,
				use: ['style-loader', 'css-loader']
			},
			{
				test: /\.ttf$/,
				use: ['file-loader']
			}
		]
	},
	plugins: [new MonacoWebpackPlugin({
		languages: ['clojure'],
		features: ['editor'],
		filename: isDev ? '[name].worker.js' : '[name].[contenthash].worker.js'
	})],
  output: {
    path: BUILD_DIR,
    filename: (data) => {
			if (data.chunk.name === 'main') {
				return isDev ? 'deps.js' : 'deps.[contenthash].js';
			}

			return isDev ? '[name].js' : '[name].[contenthash].js';
		}
  },
};

module.exports = config;
