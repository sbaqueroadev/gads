/**
 * 
 */
var personRecordApp = angular.module("personRecordApp", []);

/************************ LIST CONTROLLER****************************************/
personRecordApp.controller("recordListCtrlr", ['$scope','$q','$http',function($scope,$q,$http) {
	$scope.loadData = function(){
		$http.get("./")
		.then(function success(response){
			$scope.records = response.data;
		},
		function error(response){
			$scope.records = [];
		});
	};
	$scope.loadData();
}]);