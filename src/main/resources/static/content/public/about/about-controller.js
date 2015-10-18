/**
 * Created by cauchywei on 15/10/13.
 */
soja.controller('AboutController', ['$scope','menu',function ($scope,menu) {

    $scope.init = function () {
        menu.selectIndex(4);
    };

}]);