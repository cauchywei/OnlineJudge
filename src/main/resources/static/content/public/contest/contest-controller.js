/**
 * Created by cauchywei on 15/10/13.
 */
soja.controller('ContestController', ['$scope','menu',function ($scope,menu) {

    $scope.init = function () {
        menu.selectIndex(2);
    };

}]);