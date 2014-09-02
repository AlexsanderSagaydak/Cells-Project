/**
 * User: kuzmenko
 * Date: 3/25/14
 */
'use strict';

var controllers = angular.module('controllers', []);

controllers.controller('HeaderCtrl', ['$scope', '$location',
    function($scope, $location) {
        $scope.isActive = function(isActive) {
            if (isActive == true) {
                return true
            } else {
                return false
            }
        };
    }
]);

controllers.controller('SafeListCtrl', ['$scope', '$routeParams', 'Safe',
    function($scope, $routeParams, Safe) {
        $scope.safes = Safe.query({status: $routeParams.status});
        $scope.tableHead = [
            {name: 'code', title: 'Код сейфа'},
            {name: 'model', title: 'Модель'},
            {name: 'height', title: 'Высота, мм'},
            {name: 'width', title: 'Ширина, мм'},
            {name: 'depth', title: 'Глубина, мм'},
            {name: 'department', title: 'Отделение'},
            {name: 'price', title: 'Цена, грн'},
            {name: 'editing', title: 'Редактирование'}
        ];
        $scope.activeStatus = $routeParams.status;
    }
]);

controllers.controller('SafeEditCtrl', ['$scope', '$routeParams', '$location', 'Safe', 'Department', 'Model',
    function($scope, $routeParams, $location, Safe, Department, Model) {
        $scope.safe = Safe.get({id: $routeParams.id});
        $scope.models = Model.query();
        $scope.departments = Department.query();
        $scope.save = function() {
            $scope.safe.$save(function() {
                $location.path('list/f');
            })
        }
    }
]);

controllers.controller('SafeNewCtrl', ['$scope', '$location', '$http', 'Safe',
    function($scope, $location, $http, Safe) {
        $scope.safe = {};
        $http.get('model').success(function(response) {
            $scope.models = response;
        });
        $http.get('department').success(function(response) {
            $scope.departments = response;
        });
        $scope.save = function() {
            var safe = new Safe($scope.safe);
            safe.$save(function() {
                $location.path('list/f');
            })
        }
    }
]);

controllers.controller('ModelListCtrl', ['$scope', 'Model',
    function($scope, Model) {
        $scope.models = Model.query();
        $scope.tableHead = [
            {name: 'name', title: 'Название модели'},
            {name: 'height', title: 'Высота, мм'},
            {name: 'width', title: 'Ширина, мм'},
            {name: 'depth', title: 'Глубина, мм'},
            {name: 'editing', title: 'Редактирование'}
        ];
    }
]);

controllers.controller('ModelEditCtrl', ['$scope', '$location', '$routeParams', 'Model',
    function($scope, $location, $routeParams, Model) {
        $scope.model = Model.get({id: $routeParams.id});
        $scope.save = function() {
            $scope.model.$save(function() {
                $location.path('/list');
            })
        };
    }
])

controllers.controller('ModelNewCtrl', ['$scope', '$location', 'Model',
    function($scope, $location, Model) {
        $scope.model = {};
        $scope.save = function() {
            var model = new Model($scope.model);
            model.$save(function() {
                $location.path('/list');
            })
        };
    }
]);

controllers.controller('ClientListCtrl', ['$scope', '$rootScope', '$routeParams', 'Client',
    function($scope, $rootScope, $routeParams, Client) {
        $scope.clients = Client.query();
        $scope.tableHead = [
            {name: 'id', title: "ID"},
            {name: 'surname', title: 'ФИО'},
            {name: 'birthday', title: 'Дата рождения'},
            {name: 'phone', title: 'Телефон'},
            {name: 'inn', title: 'ИНН'},
            {name: 'series', title: 'Серия паспорта'},
            {name: 'number', title: 'Номер паспорта'},
            {name: 'isResident', title: 'Резидент'},
            {name: 'editing', title: 'Редактирование'}
        ];
    }
]);

controllers.controller('ClientEditCtrl', ['$scope', '$location', '$routeParams', 'Client',
    function($scope, $location, $routeParams, Client) {
        $scope.client = Client.get({id: $routeParams.id});
        $scope.save = function() {
            $scope.client.$save(function() {
                $location.path('/list');
            });
        };
    }
]);

controllers.controller('ClientNewCtrl', ['$scope', '$location', 'Client',
    function($scope, $location, Client) {
        $scope.client = {};
        $scope.save = function() {
            var client = new Client($scope.client);
            client.$save(function() {
                $location.path('/list');
            });
        };
    }
]);
