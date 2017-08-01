var reportingAllApp = angular.module('reportingAllApp', ['ngRoute']);

reportingAllApp.config(function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl	: 'client/views/home.html',
			controller	: 'mainController'
		})
		.otherwise({
			redirectTo: '/'
		});
});

reportingAllApp.controller('mainController', function($scope) {
	
});