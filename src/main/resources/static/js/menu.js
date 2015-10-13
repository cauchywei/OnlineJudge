/**
 * Created by cauchywei on 15/10/13.
 */
ojapp.factory('menu',['$location',function ($location) {
    var self = this;
    var items =
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