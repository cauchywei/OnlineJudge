var soja = angular.module('soja', ['ngRoute', 'ngMaterial','ngMessages','auth'])

    .config(function ($routeProvider, $httpProvider, $locationProvider) {

        $routeProvider.when('/', {
            templateUrl: 'content/public/home/home.html'
        }).when('/register', {
            templateUrl: 'content/public/auth/register.html',
            controller:'RegisterController'
        }).when('/login', {
            templateUrl: 'content/public/auth/login.html',
            controller:'LoginController'
        }).when('/library', {
            templateUrl: 'content/public/library/library.html',
            controller: 'LibraryController'
        }).when('/contest', {
            templateUrl: 'content/public/contest/contest.html',
            controller: 'ContestController'
        }).when('/rank', {
            templateUrl: 'content/public/rank/rank.html',
            controller: 'RankController'
        }).when('/about', {
            templateUrl: 'content/public/about/about.html',
            controller: 'AboutController'
        }).otherwise('/');

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        });

    })
    .config(function ($mdThemingProvider, $mdIconProvider) {

        $mdIconProvider
            .defaultIconSet()
            .icon("home", "./assets/svg/ic_home.svg", 24)
            .icon("library", "./assets/svg/ic_library.svg", 24)
            .icon("contest", "./assets/svg/ic_contest.svg", 24)
            .icon("rank", "./assets/svg/ic_rank.svg", 24)
            .icon("about", "./assets/svg/ic_about.svg", 24)


        $mdThemingProvider.theme('default');
        //.primaryPalette('brown')
        //.accentPalette('pink');

    })
    //.run(['auth',function (auth) {
    //
    //}]);

soja.factory("appinfo", function () {

    return {
        name: 'SOJA'
    }
});

soja.factory('TokenStorage', function () {
    var storageKey = 'auth_token';
    return {
        store: function (token) {
            return localStorage.setItem(storageKey, token);
        },
        retrieve: function () {
            return localStorage.getItem(storageKey);
        },
        clear: function () {
            return localStorage.removeItem(storageKey);
        }
    };
}).factory('TokenAuthInterceptor', ['$q', '$location', 'TokenStorage', function ($q, $location, TokenStorage) {
    return {
        request: function (config) {
            var authToken = TokenStorage.retrieve();
            if (authToken) {
                config.headers['X-AUTH-TOKEN'] = authToken;
            }
            return config;
        },
        responseError: function (error) {
            if (error.status === 401 || error.status === 403) {
                TokenStorage.clear();
                //$location.path('/login')
            }
            return $q.reject(error);
        }
    };
}]).config(function ($httpProvider) {
    $httpProvider.interceptors.push('TokenAuthInterceptor');
});


soja.controller('AppController', ['$scope', '$http', 'TokenStorage', 'menu','auth', function ($scope, $http, TokenStorage, menu,auth) {
        $scope.menu = menu;
        $scope.init = function () {
            auth.init('/','/login','/register');
        };

        $scope.auth = auth;
}]);