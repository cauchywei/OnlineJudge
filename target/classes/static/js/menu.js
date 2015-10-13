/**
 * Created by cauchywei on 15/10/13.
 */
app.factory('menu', ['$location', '$rootScope', '$http', '$window', function ($location, $rootScope, $http, $window) {

    var self = this;
    this.items =
        [
            {
                name: '主页',
                url: '/'
            },
            {
                name: '题库',
                url: '/library'
            },
            {
                name: '比赛',
                url: '/contest'
            },
            {
                name: '排名',
                url: '/rank'
            },
            {
                name: '关于',
                url: 'about'
            }];

    this.currentItem = items[0];

    this.selectItem = function (item) {
        self.currentItem = item;
    };

    this.isSeleted = function (item) {
        return self.currentItem == item;
    };

}]);