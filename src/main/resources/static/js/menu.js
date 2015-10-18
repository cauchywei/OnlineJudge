/**
 * Created by cauchywei on 15/10/13.
 */
soja.factory('menu', ['$location', function ($location) {
    var self = this;
    var items =
        [
            {
                name: '主页',
                url: '/',
                icon: 'home'
            },
            {
                name: '题库',
                url: '/library',
                icon: 'library'
            },
            {
                name: '比赛',
                url: '/contest',
                icon: 'contest'
            },
            {
                name: '排名',
                url: '/rank',
                icon: 'rank'
            },
            {
                name: '关于',
                url: '/about',
                icon: 'about'
            }];


    return self = {
        items: items,
        currentItem: {},
        selectIndex: function (index) {
            if (index >= 0 && index < items.length) {
                self.currentItem = items[index];
            }
        },
        selectItem: function (item) {
            self.currentItem = item;
            $location.path(item.url);
        },

        isSelected: function (item) {
            return self.currentItem === item;
        }
    }

}]);