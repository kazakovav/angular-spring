var argv = require('yargs').argv;

var environment = argv.env || 'dev';

function getConfigKeys() {
	var keys;
	try {
		keys = require('./' + environment + '-config');
	} catch (e) {
		throw new Error('No config file found for environment ' + environment);
	}
	keys.environment = environment;
	return keys;
}

module.exports.environment = environment;
module.exports.getConfigKeys = getConfigKeys;