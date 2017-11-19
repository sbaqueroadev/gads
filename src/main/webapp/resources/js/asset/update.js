/**
 * 
 */
var assetUpdateApp = angular.module("assetUpdateApp", []);

/************************ LIST CONTROLLER****************************************/
assetUpdateApp.controller("assetUpdateCtrlr", ['$scope','$q','$http',function($scope,$q,$http) {
	$scope.asset = {};
	/************************ SEND DATA PROCESS****************************************/
	$scope.serialChanged = function(){
		$http.get("../asset?serial="+$scope.asset.serial)
		.then(function success(response){
			$scope.init();
			var serialAux = $scope.asset.serial;
			$scope.asset = {};
			$scope.asset.serial = serialAux;
			if(response.data.length>0){
				$scope.asset = response.data[0];
				var date = new Date();
				if($scope.asset.buyDate){
					date.setTime($scope.asset.buyDate);
					$scope.asset.buyDate = date;
				}
				if($scope.asset.withdrawalDate){
					date.setTime($scope.asset.withdrawalDate);
					$scope.asset.withdrawalDate = date;
				}
				if($scope.asset.assignedAsset.person)
					$scope.asset.assignedAsset.person.id=""+$scope.asset.assignedAsset.person.id;
				if($scope.asset.assignedAsset.area)
					$scope.asset.assignedAsset.area.id=""+$scope.asset.assignedAsset.area.id;
				$("#updateAssetForm input").removeAttr("disabled");
				$("#updateAssetForm select").removeAttr("disabled");
			}
		},
		function error(response){
			
		});
	};
	$scope.assignedSelected=function(type){
		switch(type){
		case 'person':
			$scope.asset.assignedAsset.area = {};
			break;
		case 'area':
			$scope.asset.assignedAsset.person = {};
			break;
		}
	};
	$scope.send = function(){
		sendAsset().then(function(result){});
	};
	$scope.init = function(){
		$("#updateAssetForm input").not('input[name="serial"]')
		.not('input[type="submit"]')
		.attr("disabled","disabled");
		$("#updateAssetForm select").attr("disabled","disabled");
	}
	$scope.init();
	$scope.loadData = function(){
		$http.get("../person")
		.then(function success(response){
			$scope.persons = response.data;
		},
		function error(response){
			$scope.persons = [];
		});
		$http.get("../area")
		.then(function success(response){
			$scope.areas = response.data;
		},
		function error(response){
			$scope.areas = [];
		});
	};
	$scope.loadData();
	/********************************************************************************/
	/************************ SEND NEW ORDER****************************************/
	function sendAsset(){
		var deferred = $q.defer();
		var data = $scope.asset;
		$http.put("../asset",JSON.stringify(data))
		.then(function success(response){
			if(response.data.result=="OK"){
				$scope.asset={};
				$("#updateAssetForm").trigger("reset");
				$scope.init();
				alert("Update correctly!");
			}else
				alert("Error. Try later please.");
			deferred.resolve(response.data);
		},
		function error(response){
			deferred.resolve();//[{id:1,name:"P1"}]);
		});
		return deferred.promise;
	}
	/********************************************************************************/
}]);
/********************************************************************************/
