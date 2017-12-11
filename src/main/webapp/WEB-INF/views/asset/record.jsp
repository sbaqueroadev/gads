<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
	<h2 class="title"><spring:message code="asset.search.title"/></h2>
	<jsp:include page="../home/menu.jsp"></jsp:include> </header>
	<div id="content" ng-app='assetRecordApp'
		ng-controller="recordListCtrlr" ng-init='records = []' class="row">
		<div class="col-md-offset-2 col-md-4">
			<label for="asset"> <spring:message code="asset.serial"/>: </label> <select class="form-control"
				name="serial" data-ng-model="serial"
				data-ng-options="record.serial for record in records">
				<!-- <option ng-repeat="customer in customers" value="{{customer.name}}">{{customer.name}}</option>-->
			</select>
		</div>
		<div class="col-md-4">
			<label for="asset"> <spring:message code="asset.type"/>: </label> <select class="form-control"
				name="type" data-ng-model="type"
				data-ng-options="record.type for record in records">
				<!-- <option ng-repeat="customer in customers" value="{{customer.name}}">{{customer.name}}</option>-->
			</select>
		</div>
		<div class="col-md-offset-2 col-md-4">
			<label for="dateFrom"> <spring:message code="custom.from"/>: </label> <input class="form-control"
				type="date" name="dateFrom" ng-model="dateFrom"
				value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" />
		</div>
		<div class="col-md-4">
			<label for="dateTo"> <spring:message code="custom.to"/>: </label> <input class="form-control"
				type="date" name="dateTo" ng-model="dateTo"
				value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>" />
		</div>
		<div class="col-md-offset-1 col-md-10">
			<br>
			<br>
			<table class="table table-bordered table-striped records-table">

				<thead>
					<tr>
						<td><spring:message code="asset.name"/></td>
						<td><spring:message code="asset.serial"/></td>
						<td><spring:message code="asset.type"/></td>
						<td><spring:message code="asset.status"/></td>
						<td><spring:message code="asset.inventoryNumber"/></td>
						<td><spring:message code="asset.buyDate"/></td>
						<td><spring:message code="asset.buyPrice"/></td>
						<td><spring:message code="asset.color"/></td>
						<td><spring:message code="asset.width"/></td>
						<td><spring:message code="asset.length"/></td>
						<td><spring:message code="asset.height"/></td>
						<td><spring:message code="asset.weight"/></td>
						<td><spring:message code="asset.withDDate"/></td>
					</tr>
				</thead>

				<tbody>
					<c:if test="${empty data}">
						<tr>
							<td colspan="13"><spring:message code="custom.noData"/></td>
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