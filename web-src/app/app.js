/**
 * @author Andrey Kazakov
 */
(function () {
	'use strict';

	angular.module('sampleAngularApp', ['ui.bootstrap', 'pascalprecht.translate', 'ngRoute', 'ngSanitize',
		'ngCookies', 'user.service', 'user.login', 'user.login.service', 'user.home', 'users.controller']);

	angular.module('sampleAngularApp').config(
		[ '$routeProvider', '$httpProvider', 'userResourceProvider', 'loginServiceProvider', '$translateProvider',
		function ($routeProvider, $httpProvider, userResourceProvider, loginServiceProvider, $translateProvider) {
			$routeProvider.when('/', {
				templateUrl: 'home/home.html',
				controller: 'HomeController',
				controllerAs: 'home'
			}).when('/login', {
				templateUrl: 'login/login.html',
				controller: 'LoginController',
				controllerAs: 'login'
			}).when('/users', {
				templateUrl: 'user/users.html',
				controller: 'UsersController',
				controllerAs: 'users'
			}).otherwise('/');

			loginServiceProvider.setLoginUrl('api/authenticate');
			loginServiceProvider.setLogoutUrl('logout');

			userResourceProvider.setUserCurrentUrl('api/users/current');
			userResourceProvider.setUserDefaultUrl('api/users');

			$translateProvider.useStaticFilesLoader({
				prefix: 'resources/i18n/',
				suffix: '.json'
			});

			$translateProvider.registerAvailableLanguageKeys(['en', 'ru'], {
				'en-US': 'en',
				'en-UK': 'en',
				'ru-RU': 'ru'
			});


			$translateProvider.useSanitizeValueStrategy('sanitize');
			$translateProvider.fallbackLanguage('en');
			$translateProvider.preferredLanguage('en');
			$translateProvider.uniformLanguageTag('bcp47');
			$translateProvider.determinePreferredLanguage();
			$translateProvider.useLocalStorage();

			$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
		}]);

	function NavigationController($route, $location, $translate, userService, loginService) {
		var that = this;
		that.locales = ['en', 'ru'];
		that.currentLocale = $translate.use() || $translate.storage().get($translate.storageKey()) || $translate.preferredLanguage();

		that.tab = function (route) {
			return $route.current && route === $route.current.controller;
		};

		that.getClass = function (path) {
			if (path === '/') {
				if ($location.path() === '/') {
					return "active";
				} else {
					return "";
				}
			}

			if ($location.path().substr(0, path.length) === path) {
				return "active";
			} else {
				return "";
			}
		};

		that.logout = function () {
			loginService.logoutUser(function () {
				userService.reset();
				$location.path("/");
			});
		};

		that.isAuthenticated = function () {
			return userService.isAuthenticated();
		};

		that.setLocale = function (locale) {
			$translate.use(locale);
			$translate.refresh();
			that.currentLocale = locale;
		};

		that.isUserHasRole = function (role) {
			return userService.isUserHasRole(role);
		};
	}

	angular.module('sampleAngularApp').controller('NavigationController',
		['$route', '$location', '$translate', 'userService', 'loginService', NavigationController]);
})();
