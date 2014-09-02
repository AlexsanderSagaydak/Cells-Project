/**
 * User: kuzmenko
 * Date: 3/27/14
 */

'use strict';

var clientsApp = angular.module('clientsApp', [
    'ngRoute',
    'controllers',
    'services'
]);

clientsApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
                when('/list', {
                    templateUrl: 'views/client-list.html',
                    controller: 'ClientListCtrl'
                }).
                when('/edit/:id', {
                    templateUrl: 'views/client-edit.html',
                    controller: 'ClientEditCtrl'
                }).
                when('/new', {
                    templateUrl: 'views/client-edit.html',
                    controller: 'ClientNewCtrl'
                }).
                otherwise({
                    redirectTo: '/list'
                });
    }]);

