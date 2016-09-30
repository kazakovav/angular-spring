/**
 * Login service
 */
(function () {
	function LoginServiceProvider() {
		var $this = this;

		var loginUrl = '/api/authenticate';
		var logoutUrl = 'logout';

		$this.setLoginUrl = function (url) {
			loginUrl = url;
		};

		$this.setLogoutUrl = function (url) {
			logoutUrl = url;
		};

		$this.$get = ['$http', function ($http) {
			return {
				loginUser: function (credentials, callback) {
					var headers = credentials ? {
						authorization: "Basic "
						+ btoa(credentials.username + ":"
							+ credentials.password)
					} : {};


					$http.get(loginUrl, {
						headers: headers
					}).success(function (data) {
						if (data['success']) {
							callback && callback(true);
							return
						}
						callback && callback(false);
					}).error(function (data) {
						callback && callback(false, data['failure']);
					});
				},

				logoutUser: function (callback) {
					$http.post('logout', {}).success(function () {
						callback && callback(true);
					}).error(function (data) {
						callback && callback(false);
					});
				}
			};
		}];
	}

	angular.module('user.login.service', []).provider('loginService', [LoginServiceProvider]);
})();