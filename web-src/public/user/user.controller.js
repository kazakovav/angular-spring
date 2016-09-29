/**
 *
 */
(function () {
	'use strict';
	function UsersController(userService) {
		var self = this;

		userService.getAllUsers(function (usersData) {
			self.users = usersData;
		});

	}

	angular.module('users.controller', []).controller('UsersController', ['userService', UsersController]);
})();
