<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sergio Baquero - Activo</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" href="../resources/css/custom.css">
</head>
<body>
	<header>
	<h2 class="title">Crear un activo</h2>
	<%@include file="../home/menu.html"%> </header>
	<div id="content" ng-app='assetAddApp'>
		<form id="addAssetForm" ng-controller="assetAddCtrlr as vm"
			ng-submit="send()" ng-init="asset = {}"
			class="col-md-offset-1 col-md-10">
			<div class="row">
			<div class="col-md-4">
				<label for="name">Nombre:</label> <input class="form-control"
					id="name" type="text" name="name" ng-model="asset.name" required />
			</div>
			<div class="col-md-4">
				<label for="serial">Serial:</label> <input class="form-control"
					id="serial" type="text" name="serial" ng-model="asset.serial"
					required />
			</div>
			<div class="col-md-4">
				<label for="type">Tipo:</label> <input class="form-control"
					id="type" type="text" name="type" ng-model="asset.type" required />
			</div>
			<div class="col-md-4">
				<label for="status">Estado:</label> <select class="form-control"
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
				<label for="inventoryNumber">Número de inventario:</label> <input
					class="form-control" id="inventoryNumber" type="text"
					name="inventoryNumber" ng-model="asset.inventoryNumber" required />
			</div>
			<div class="col-md-4">
				<label for="buyDate">Fecha de compra:</label> <input
					class="form-control" id="buyDate" type="date" name="buyDate"
					ng-model="asset.buyDate" required />
			</div>
			<div class="col-md-4">
				<label for="buyPrice">Precio de compra:</label> <input
					class="form-control" id="buyPrice" type="number" step="0.01"
					name="buyPrice" ng-model="asset.buyPrice" required />
			</div>
			<div class="col-md-4">
				<label for="color">Color:</label> <input class="form-control"
					id="color" type="text" name="color" ng-model="asset.color" required />
			</div>
			<div class="col-md-4">
				<label for="width">Ancho:</label> <input class="form-control"
					id="width" type="number" step="0.01" name="width"
					ng-model="asset.width" required />
			</div>
			<div class="col-md-4">
				<label for="length">Largo:</label> <input class="form-control"
					id="length" type="number" step="0.01" name="length"
					ng-model="asset.length" required />
			</div>
			<div class="col-md-4">
				<label for="height">Altura:</label> <input class="form-control"
					id="height" type="number" step="0.01" name="height"
					ng-model="asset.height" required />
			</div>
			<div class="col-md-4">
				<label for="weight">Peso:</label> <input class="form-control"
					id="weight" type="number" step="0.01" name="weight"
					ng-model="asset.weight" required />
			</div>
			<div class="col-md-4">
				<label for="withdrawalDate">Fecha de baja:</label> <input
					class="form-control" id="withdrawalDate" type="date"
					name="withdrawalDate" ng-model="asset.withdrawalDate" />
			</div>
			</div><div class="row">
			<div class="col-md-offset-1 col-md-2">
			<br>
				<input class="form-control btn btn-success" type="submit"
					value="Enviar" />
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
	<script src="../resources/js/asset/add.js" type="text/javascript"></script>

</body>
</html>