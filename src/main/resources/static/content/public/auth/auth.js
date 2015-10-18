/**
 * Created by cauchywei on 15/10/17.
 */
angular.module('auth', []).factory('auth', ['$rootScope', '$http', '$location', 'TokenStorage',
    function ($rootScope, $http, $location, TokenStorage) {


        var auth = {
            authenticated: false,
            loginPath: '/login',
            logoutPath: '/logout',
            homePath: '/',
            path: $location.path(),
            user: {},
            logining: false,
            registering: false,

            enter: function () {
                if ($location.path != auth.loginPath) {
                    auth.path = $location.path();
                    if (!auth.authenticated) {
                        $location.path(auth.loginPath);
                    }
                }
            },

            authenticate: function (credentials, callback) {
                logining = true;
                $http.post('/api/login', {
                    username: credentials.username,
                    password: credentials.password
                }).success(function (result, status, headers) {
                    auth.logining = false;
                    auth.authenticated = true;
                    auth.user = result;
                    TokenStorage.store(headers('X-AUTH-TOKEN'));
                    callback && callback(result);
                    $location.path(auth.path == auth.loginPath ? auth.homePath : auth.path);
                }).error(function () {
                    auth.logining = false;
                    auth.authenticated = false;
                    callback && callback(false);
                });
            },

            clear: function () {
                //$location.path(auth.loginPath);
                auth.authenticated = false;
                TokenStorage.clear();
            },

            init: function (homePath, loginPath, logoutPath) {

                auth.homePath = homePath;
                auth.loginPath = loginPath;
                auth.logoutPath = logoutPath;


                $http.get('/api/users/current').success(function (user) {
                    if (user.username !== 'anonymousUser') {
                        auth.authenticated = true;
                        auth.user.username = user.username;

                    }
                });


            }

        };

        return auth;

    }]);