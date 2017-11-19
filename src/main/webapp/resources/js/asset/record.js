/**
 * 
 */
var assetRecordApp = angular.module("assetRecordApp", []);

/************************ LIST CONTROLLER****************************************/
assetRecordApp.controller("recordListCtrlr", ['$scope','$q','$http',function($scope,$q,$http) {
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
/********************************************************************************/
/************************ CUSTOM FILTERS****************************************/
/************************ CREATION DATE FILTER****************************************/
assetRecordApp.filter("byDate",function(){
	return function(items, from, to) {
		var result = [];
		if(from && to){
	        var df = from;
	        var dt = to;
	        df.setHours(0,0,0,0);
	        dt.setHours(23,59,59,999);        
	        for (var i=0; i<items.length; i++){
	            var tf = new Date();
	            tf.setTime(items[i].buyDate);
	            if (tf >= df && tf <= dt)  {
	                result.push(items[i]);
	            }
	        }   
		}
		else
			result = items;
        return result;
  };
});
/********************************************************************************/
/************************ CUSTOMER SERIAL FILTER****************************************/
assetRecordApp.filter("bySerial",function(){
	return function(items, asset) {
		var result = [];
		if(asset)
			$.each(items,function(index,item){
				if (item.serial.indexOf(asset.serial)>-1)  {
	                result.push(item);
				}            
	        });
		else
			result = items;
        return result;
  };
});
/********************************************************************************/
/************************ CUSTOMER TYPE FILTER****************************************/
assetRecordApp.filter("byType",function(){
	return function(items, asset) {
		var result = [];
		if(asset)
			$.each(items,function(index,item){
				if (item.type.indexOf(asset.type)>-1)  {
	                result.push(item);
				}            
	        });
		else
			result = items;
        return result;
  };
});