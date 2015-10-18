/**
 * Created by cauchywei on 15/10/18.
 */
soja.controller('HomeController', ['$scope','menu',function ($scope,menu) {

    $scope.init = function () {
        menu.selectIndex(0);
    };

}]);