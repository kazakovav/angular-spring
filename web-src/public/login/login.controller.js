/**
 * Login controller
 */
(function () {
	'use strict';
	function LoginController($location, loginService, userService) {
		var self = this;
		self.credentials = {};
		self.login = function () {
			loginService.loginUser(self.credentials, function (authenticated, message) {
				if (authenticated) {
					userService.reset();
					$location.path("/");
					self.error = false;
				} else {
					userService.reset();
					$location.path("/login");
					self.error = true;
					self.errorMessage = message;
				}
			});
		};
	}

	angular.module('user.login', ['user.login.service', 'user.service']).controller('LoginController', ['$location', 'loginService', 'userService', LoginController]);
})();
