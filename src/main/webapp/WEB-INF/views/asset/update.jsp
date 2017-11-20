<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sergio Baquero - <spring:message code="menu.assets"/></title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" href="../resources/css/custom.css">
</head>
<body>
	<header>
	<spring:message code="custom.language"/> : <a href="?lang=en"><spring:message code="language.english"/></a> | <a href="?lang=es"><spring:message code="language.spanish"/></a>
	<h2 class="title"><spring:message code="asset.update.title"/></h2>
	<jsp:include page="../home/menu.jsp"></jsp:include> </header>
	<div id="content" ng-app='assetUpdateApp'>
		<form id="updateAssetForm" ng-controller="assetUpdateCtrlr as vm"
			ng-submit="send()" ng-init="asset = {}"
			class="col-md-offset-1 col-md-10">
			<div class="row">
				<input id="id" type="hidden" name="type" ng-model="asset.id" />
				<div class="col-md-4">
					<label for="serial"><spring:message code="asset.serial"/>:</label> <input class="form-control"
						id="serial" type="text" name="serial" ng-model="asset.serial"
						ng-change="serialChanged()" required />
				</div>
				<div class="col-md-4">
					<label for="name"><spring:message code="asset.name"/>:</label> <input class="form-control"
						id="name" type="text" name="name" ng-model="asset.name" required />
				</div>
				<div class="col-md-4">
					<label for="type"><spring:message code="asset.type"/>:</label> <input class="form-control"
						id="type" type="text" name="type" ng-model="asset.type" required />
				</div>
				<div class="col-md-4">
					<label for="status"><spring:message code="asset.status"/>:</label> <select class="form-control"
						id="status" name="status" ng-model="asset.status" required>
						<c:if test="${empty assetStatus}">
							<option value="error">Error</option>
						</c:if>
						<c:if test="${not empty assetStatus}">

							<c:forEach items="${assetStatus}" var="status">
								<option value="${status.id}">${status.id}</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
				<div class="col-md-4">
					<label for="inventoryNumber"><spring:message code="asset.inventoryNumber"/>:</label> <input
						class="form-control" id="inventoryNumber" type="text"
						name="inventoryNumber" ng-model="asset.inventoryNumber" required />
				</div>
				<div class="col-md-4">
					<label for="buyDate"><spring:message code="asset.buyDate"/>:</label> <input
						class="form-control" id="buyDate" type="date" name="buyDate"
						ng-model="asset.buyDate" required />
				</div>
				<div class="col-md-4">
					<label for="buyPrice"><spring:message code="asset.buyPrice"/>:</label> <input
						class="form-control" id="buyPrice" type="number" step="0.01"
						name="buyPrice" ng-model="asset.buyPrice" required />
				</div>
				<div class="col-md-4">
					<label for="color"><spring:message code="asset.color"/>:</label> <input class="form-control"
						id="color" type="text" name="color" ng-model="asset.color"
						required />
				</div>
				<div class="col-md-4">
					<label for="width"><spring:message code="asset.width"/>:</label> <input class="form-control"
						id="width" type="number" step="0.01" name="width"
						ng-model="asset.width" required />
				</div>
				<div class="col-md-4">
					<label for="length"><spring:message code="asset.length"/>:</label> <input class="form-control"
						id="length" type="number" step="0.01" name="length"
						ng-model="asset.length" required />
				</div>
				<div class="col-md-4">
					<label for="height"><spring:message code="asset.height"/>:</label> <input class="form-control"
						id="height" type="number" step="0.01" name="height"
						ng-model="asset.height" required />
				</div>
				<div class="col-md-4">
					<label for="weight"><spring:message code="asset.weight"/>:</label> <input class="form-control"
						id="weight" type="number" step="0.01" name="weight"
						ng-model="asset.weight" required />
				</div>
				<div class="col-md-4">
					<label for="withdrawalDate"><spring:message code="asset.withDDate"/>:</label> <input
						class="form-control" id="withdrawalDate" type="date"
						name="withdrawalDate" ng-model="asset.withdrawalDate" />
				</div>

				<div class="col-md-4">
					<spring:message code="asset.assignTo"/>:<br> <label for="person"><spring:message code="person.person"/>:</label> <select
						class="form-control" id="person" name="person"
						ng-model="asset.assignedAsset.person.id"
						ng-change="assignedSelected('person')">
						<option ng-repeat="person in persons" value="{{person.id}}">{{person.id}}
							- {{person.name}}</option>
					</select> <br> <label for="area"><spring:message code="area.area"/>:</label> <select
						class="form-control" id="area" name="area"
						ng-model="asset.assignedAsset.area.id"
						ng-change="assignedSelected('area')">
						<option ng-repeat="area in areas" value="{{area.id}}">{{area.id}}
							- {{area.name}}, {{area.city.name}}</option>
					</select>
				</div>
			</div>
			<div class="row">
				<div class="col-md-offset-1 col-md-2">
					<br> <input class="form-control btn btn-success" type="submit"
						value="<spring:message code="custom.update"/>" />
				</div>
			</div>
		</form>
	</div>
	<script src="http://code.jquery.com/jquery-latest.min.js"
		type="text/javascript"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
	<script src="../resources/js/asset/update.js" type="text/javascript"></script>

</body>
</html>