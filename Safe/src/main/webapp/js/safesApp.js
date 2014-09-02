/**
 * Created with IntelliJ IDEA.
 * User: kuzmenko
 * Date: 3/25/14
 */
'use strict';

var safesApp = angular.module('safesApp', [
    'ngRoute',
    'controllers',
    'services'
]);

safesApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
                when('/list/:status', {
                    templateUrl: 'views/safe-list.html',
                    controller: 'SafeListCtrl'
                }).
                when('/edit/:id', {
                    templateUrl: 'views/safe-edit.html',
                    controller: 'SafeEditCtrl'
                }).
                when('/new', {
                    templateUrl: 'views/safe-edit.html',
                    controller: 'SafeNewCtrl'
                }).
                otherwise({
                    redirectTo: '/list/f'
                });
    }]);