/**
 * Area Record Angular App
 */
var areaRecordApp = angular.module("areaRecordApp", []);

/************************ LIST CONTROLLER****************************************/
areaRecordApp.controller("recordListCtrlr", ['$scope','$q','$http',function($scope,$q,$http) {
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