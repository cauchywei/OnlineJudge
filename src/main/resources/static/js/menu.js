/**
 * Created by cauchywei on 15/10/13.
 */
soja.factory('menu',['$location',function ($location) {
    var self = this;
    var items =
        [
            {
                name: '主页',
                url: '/',
                icon:'home'
            },
            {
                name: '题库',
                url: '/library',
                icon:'library'
            },
            {
                name: '比赛',
                url: '/contest',
                icon:'contest'
            },
            {
                name: '排名',
                url: '/rank',
                icon:'rank'
            },
            {
                name: '关于',
                url: '/about',
                icon:'about'
            }];

    var currentItem = items[0];

    return self = {
        items: items,
        currentItem: currentItem,
        selectItem: function (item) {
            self.currentItem = item;
            $location.path(item.url);
        },

        isSelected: function (item) {
            return self.currentItem === item;
        }
    }


}]);