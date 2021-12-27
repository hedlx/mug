const path = require('path');

const BUILD_DIR = path.resolve(__dirname, 'resources', 'public', 'js');
const APP_DIR = path.resolve(__dirname, 'src', 'js');

const config = {
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
  output: {
    path: BUILD_DIR,
    filename: 'npm-deps-bundle.js'
  },
};

module.exports = config;
