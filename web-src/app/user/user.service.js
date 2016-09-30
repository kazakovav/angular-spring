/**
 * Storage for sharing user data between views and controllers
 */
(function () {
	'use strict';
	function UserService(userResource) {
		var self = this;
		var currentUser = userResource.current();

		self.getCurrentUser = function () {
			return currentUser;
		};

		self.setCurrentUser = function (userInstance) {
			currentUser = userInstance;
		};
		self.isAuthenticated = function () {
			return !!(currentUser && currentUser.userName);

		};

		self.reset = function () {
			currentUser = userResource.current();
		};

		self.isUserHasRole = function (role) {
			for (var i in currentUser.userRoles) {
				if (!currentUser.userRoles.hasOwnProperty(i)) {
					continue;
				}
				if (currentUser.userRoles[i].role === role) {
					return true;
				}
			}
			return false;
		};

		self.getAllUsers = function (callback) {
			var users = userResource.query(function () {
				callback && callback(users);
			});
		};
	}

	function UserResourceProvider() {
		var userCurrentUrl = 'api/users/current';
		var userDefaultUrl = 'api/users';

		this.setUserCurrentUrl = function (currentUrl) {
			userCurrentUrl = currentUrl;
		};

		this.setUserDefaultUrl = function (defaultUrl) {
			userDefaultUrl = defaultUrl;
		};

		this.$get = ['$resource', function ($resource) {
			return $resource(userDefaultUrl + '/:id', {id: '@id'}, {
				current: {
					method: 'GET',
					url: userCurrentUrl,
					params: {},
					isArray: false
				}
			});
		}];
	}


	angular.module('user.service', ['ngResource']).provider('userResource', UserResourceProvider);
	angular.module('user.service').service('userService', ['userResource', UserService]);
})();
