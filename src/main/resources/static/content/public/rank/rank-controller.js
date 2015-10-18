/**
 * Created by cauchywei on 15/10/13.
 */
soja.controller('RankController', ['$scope','menu',function ($scope,menu) {

    $scope.init = function () {
        menu.selectIndex(3);
    };

}]);