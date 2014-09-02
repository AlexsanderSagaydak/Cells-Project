/**
 * Created with IntelliJ IDEA.
 * User: kuzmenko
 * Date: 3/25/14
 */
'use strict';

var services = angular.module('services', ['ngResource']);

services.factory('Safe', ['$resource',
    function($resource) {
        return $resource('safe/:status', {}, {});
    }
]);

services.factory('Model', ['$resource',
    function($resource) {
        return $resource('model/:id', {}, {});
    }
]);

services.factory('Client', ['$resource',
    function($resource) {
        return $resource('client/:id', {}, {});
    }
]);

services.factory('Department', ['$resource',
    function($resource) {
        return $resource('department/:id', {}, {});
    }
]);

services.factory('Price', ['$resource',
    function($resource) {
        return $resource('price/:id', {}, {});
    }
]);
