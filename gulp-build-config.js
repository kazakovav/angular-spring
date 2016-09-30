var argv = require('yargs').argv;

module.exports = {
	environment: argv.env || 'development',
	getConfigKeys() {
		var keys;
		try {
			keys = require(`./${this.environment}-config`);
		} catch (e) {
			throw new Error(`No config file found for environment ${this.environment}`);
		}
		keys.environment = this.environment;
		return keys;
	}
};