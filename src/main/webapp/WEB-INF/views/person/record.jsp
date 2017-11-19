<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sergio Baquero - Personas</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<%@include file="../home/menu.html"%>
	<div id="content" ng-app='personRecordApp'
		ng-controller="recordListCtrlr" ng-init='records = []'>
		<table class="table table-bordered table-striped records-table">

			<thead>
				<tr>
					<td>Id</td>
					<td>Nombre</td>
				</tr>
			</thead>

			<tbody>
				<c:if test="${empty data}">
					<tr>
						<td colspan="2">No hay personas registradas</td>
					</tr>
				</c:if>
				<c:if test="${not empty data}">
					<tr
						ng-repeat="record in records | orderBy:sortType:sortReverse">
						<td>{{ record.id }}</td>
						<td>{{ record.name 	}}</td>
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
	<script src="../resources/js/person/record.js" type="text/javascript"></script>
</body>
</html>