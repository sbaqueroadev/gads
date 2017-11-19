<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sergio Baquero - Area</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<%@include file="../home/menu.html"%>
	<div id="content" ng-app='assetRecordApp'
		ng-controller="recordListCtrlr" ng-init='records = []'>
		<div class="row">
			<div class="col-md-offset-4 col-md-4">
				<label for="asset"> Serial: </label> <select class="form-control"
					name="serial" data-ng-model="serial"
					data-ng-options="record.serial for record in records">
					<!-- <option ng-repeat="customer in customers" value="{{customer.name}}">{{customer.name}}</option>-->
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col-md-offset-4 col-md-4">
				<label for="asset"> Tipo: </label> <select class="form-control"
					name="type" data-ng-model="type"
					data-ng-options="record.type for record in records">
					<!-- <option ng-repeat="customer in customers" value="{{customer.name}}">{{customer.name}}</option>-->
				</select>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<label for="dateFrom"> From: </label> <input class="form-control"
					type="date" name="dateFrom" ng-model="dateFrom"
					value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" />
			</div>
			<div class="col-md-6">
				<label for="dateTo"> To: </label> <input class="form-control"
					type="date" name="dateTo" ng-model="dateTo"
					value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" />
			</div>
		</div>

		<table class="table table-bordered table-striped records-table">

			<thead>
				<tr>
					<td>Nombre</td>
					<td>Serial</td>
					<td>Tipo</td>
					<td>Estado</td>
					<td>Número de inventario</td>
					<td>Fecha de compra</td>
					<td>Valor de compra</td>
					<td>Color</td>
					<td>Ancho</td>
					<td>Largo</td>
					<td>Alto</td>
					<td>Peso</td>
					<td>Fecha de baja</td>
				</tr>
			</thead>

			<tbody>
				<c:if test="${empty data}">
					<tr>
						<td colspan="13">No hay activos registrados</td>
					</tr>
				</c:if>
				<c:if test="${not empty data}">
					<tr
						ng-repeat="record in records | orderBy:sortType:sortReverse | bySerial:serial |byDate:dateFrom:dateTo  | byType:type">
						<td style="display: none;">{{ record.id }}</td>
						<td>{{ record.name }}</td>
						<td>{{ record.serial }}</td>
						<td>{{ record.type }}</td>
						<td>{{ record.status }}</td>
						<td>{{ record.inventoryNumber }}</td>
						<td>{{ record.buyDate | date:format:"dd-MM-yyyy" }}</td>
						<td>{{ record.buyPrice | currency}}</td>
						<td>{{ record.color }}</td>
						<td>{{ record.width }}</td>
						<td>{{ record.length }}</td>
						<td>{{ record.height }}</td>
						<td>{{ record.weight }}</td>
						<td>{{ record.withdrawalDate | date:format:"dd-MM-yyyy" }}</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	<script src="http://code.jquery.com/jquery-latest.min.js"
		type="text/javascript"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
	<script src="../resources/js/asset/record.js" type="text/javascript"></script>
</body>
</html>