/**
 * Created with IntelliJ IDEA.
 * User: kuzmenko
 * Date: 3/12/14
 */
'use strict';


function LoginCtrl($scope, $http, $window) {
    $scope.user = {};
    $scope.authorization = function() {
        $http.post('/Safe/login', $scope.user).success(function(data) {
            if (angular.equals(data.status, 'ok')) {
                $window.location.href = '/Safe/safes';
            } else {
                $scope.authorisation = data.status;
            }
        });
    }

    $scope.getUsers = function(val) {
        if (val.length > 2) {
            return $http.post('/Safe/getUsersForAuthorisation', {
                data: val
            }).then(function(res) {
                var users = [];
                angular.forEach(res.data, function(item) {
                    users.push(item);
                });
                return users;
            });
        }
        return [];
    };

    $scope.onSelect = function($item) {
        $scope.user.login = $item.login;
    };
}
;