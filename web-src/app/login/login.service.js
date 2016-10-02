/**
 * Login service
 */
(function () {
	function LoginServiceProvider() {
		var that = this;

		var loginUrl = '/api/authenticate';
		var logoutUrl = 'logout';

		that.setLoginUrl = function (url) {
			loginUrl = url;
		};

		that.setLogoutUrl = function (url) {
			logoutUrl = url;
		};

		that.$get = ['$http', function ($http) {
			return {
				loginUser: function (credentials, callback) {
					var headers = credentials ? {
						authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)
					} : {};


					$http.get(loginUrl, {
						headers: headers
					}).success(function (data) {
						if (data['success']) {
							return callback && callback(true);
						}
						callback && callback(false);
					}).error(function (data) {
						return callback && callback(false, data['failure']);
					});
				},

				logoutUser: function (callback) {
					$http.post('logout', {}).success(function () {
						return callback && callback(true);
					}).error(function (data) {
						return callback && callback(false);
					});
				}
			};
		}];
	}

	angular.module('user.login.service', []).provider('loginService', [LoginServiceProvider]);
})();