<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sergio Baquero - <spring:message code="area.area"/></title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
	<link rel="stylesheet" href="../resources/css/custom.css">
</head>
<body>
	<header>
	<spring:message code="custom.language"/> : <a href="?lang=en"><spring:message code="language.english"/></a> | <a href="?lang=es"><spring:message code="language.spanish"/></a>
	<h2 class="title"><spring:message code="area.title"/></h2>
	<jsp:include page="../home/menu.jsp"></jsp:include>
	 </header>
	<div id="content" ng-app='areaRecordApp'
		ng-controller="recordListCtrlr" ng-init='records = []' class="row">
		<div class="col-md-offset-1 col-md-10">
			<table class="table table-bordered table-striped records-table">

				<thead>
					<tr>
						<td><spring:message code="area.id"/></td>
						<td><spring:message code="area.name"/></td>
						<td><spring:message code="area.city"/></td>
					</tr>
				</thead>

				<tbody>
					<c:if test="${empty data}">
						<tr>
							<td colspan="3"><spring:message code="custom.noData"/></td>
						</tr>
					</c:if>
					<c:if test="${not empty data}">
						<tr ng-repeat="record in records | orderBy:sortType:sortReverse">
							<td>{{ record.id }}</td>
							<td>{{ record.name }}</td>
							<td>{{ record.city.name }}</td>
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
	<script src="../resources/js/area/record.js" type="text/javascript"></script>
</body>
</html>