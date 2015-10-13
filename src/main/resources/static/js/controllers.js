var app = angular.module('SSSTAOJ', [ 'ngRoute' ]).config(function($routeProvider, $httpProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html'
	}).when('/login', {
		templateUrl : 'login.html'
	}).otherwise('/');

	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});

app.factory('TokenStorage', function() {
	var storageKey = 'auth_token';
	return {		
		store : function(token) {
			return localStorage.setItem(storageKey, token);
		},
		retrieve : function() {
			return localStorage.getItem(storageKey);
		},
		clear : function() {
			return localStorage.removeItem(storageKey);
		}
	};
}).factory('TokenAuthInterceptor', function($q,$location, TokenStorage) {
	return {
		request: function(config) {
			var authToken = TokenStorage.retrieve();
			if (authToken) {
				config.headers['X-AUTH-TOKEN'] = authToken;
			}
			return config;
		},
		responseError: function(error) {
			if (error.status === 401 || error.status === 403) {
				TokenStorage.clear();
				//$location.path('/login')
			}
			return $q.reject(error);
		}
	};
}).config(function($httpProvider) {
	$httpProvider.interceptors.push('TokenAuthInterceptor');
});

app.controller('AuthController', function ($scope, $http, TokenStorage,$log) {

	var auth = this;

	$scope.authenticated = false;
	$scope.token; // For display purposes only
	$scope.credentials = {}
	
	$scope.init = function () {
		$http.get('/api/users/current').success(function (user) {
			if(user.username !== 'anonymousUser'){
				$scope.authenticated = true;
				$scope.username = user.username;
				
				// For display purposes only
				$scope.token = JSON.parse(atob(TokenStorage.retrieve().split('.')[0]));
			}
		});
	};

	$scope.login = function () {

		$http.post('/api/login', { username: $scope.credentials.username, password: $scope.credentials.password }).success(function (result, status, headers) {
			$scope.authenticated = true;
			TokenStorage.store(headers('X-AUTH-TOKEN'));
			
			// For display purposes only
			$scope.token = JSON.parse(atob(TokenStorage.retrieve().split('.')[0]));
		});  
	};

	$scope.logout = function () {
		// Just clear the local storage
		TokenStorage.clear();	
		$scope.authenticated = false;
	};
});