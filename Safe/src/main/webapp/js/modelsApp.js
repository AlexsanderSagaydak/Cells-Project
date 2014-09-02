/**
 * User: kuzmenko
 * Date: 3/27/14
 */
'use strict';

var modelsApp = angular.module('modelsApp', [
    'ngRoute',
    'controllers',
    'services'
]);

modelsApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
                when('/list', {
                    templateUrl: 'views/model-list.html',
                    controller: 'ModelListCtrl'
                }).
                when('/edit/:id', {
                    templateUrl: 'views/model-edit.html',
                    controller: 'ModelEditCtrl'
                }).
                when('/new', {
                    templateUrl: 'views/model-edit.html',
                    controller: 'ModelNewCtrl'
                }).
                otherwise({
                    redirectTo: '/list'
                });
    }]);

