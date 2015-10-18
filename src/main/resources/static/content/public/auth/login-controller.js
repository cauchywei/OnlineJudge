/**
 * Created by cauchywei on 15/10/18.
 */
soja.controller('LoginController', ['auth','$scope','$location',function (auth,$scope,$location) {
    $scope.logining = false;
    $scope.credentials = {}

    $scope.init = function() {
        if(auth.authenticated) {
            $location.path('/home');
        }
    };

    $scope.login = function () {
        $scope.logining = true;
        auth.authenticate($scope.credentials, function (data) {
            $scope.logining = false;
            if(data) {
               $location.path('/home');
            }else {
                $scope.credentials.password = '';
                $scope.feedback = '账号或密码错误';
            }
        })
    };
}]);