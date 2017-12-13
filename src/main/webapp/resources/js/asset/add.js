/**
 * Asset adding Angular App
 */
var assetAddApp = angular.module("assetAddApp", []);

/************************ LIST CONTROLLER****************************************/
assetAddApp.controller("assetAddCtrlr", ['$scope','$q','$http','$window',function($scope,$q,$http, $window) {
	$scope.asset = {};
	/************************ SEND DATA PROCESS****************************************/
	$scope.send = function(){
		sendAsset().then(function(result){});
	};
	/********************************************************************************/
	/************************ SEND NEW ASSET****************************************/
	function sendAsset(){
		var deferred = $q.defer();
		var data = $scope.asset;
		$http.post("../asset",JSON.stringify(data))
		.then(function success(response){
			if(response.data.result=="OK"){
				$scope.asset={};
				$("#addAssetForm").trigger("reset");
				alert("Added correctly!");
			}else
				alert("Error. Try later please.");
			deferred.resolve(response.data);
		},
		function error(response){
			if(response.status=403)
				$window.location.href = '../users/create';
				//$location.path('../users/access');
			deferred.resolve();//[{id:1,name:"P1"}]);
		});
		return deferred.promise;
	}
	/********************************************************************************/
}]);
/********************************************************************************/
