/**
 * @author Andrey Kazakov
 * only show home page and fill some user info
 */
(function () {
	'use strict';
	function HomeController(userService) {
		var self = this;
		self.currentUser = userService.getCurrentUser();
		self.isAuthenticated = function () {
			var result = userService.isAuthenticated();
			return result;
		};
	}

	angular.module('user.home', []).controller('HomeController', ['userService', HomeController]);
})();
