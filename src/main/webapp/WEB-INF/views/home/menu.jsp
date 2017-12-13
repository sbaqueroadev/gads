<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Prueba Sergio Baquero</a>
		</div>
		<ul class="nav navbar-nav nav-pills">
			<li class="nav-item dropdown">
				<a
				class="nav-link dropdown-toggle" data-toggle="dropdown"
				href="asset/home" aria-haspopup="true"
				aria-expanded="false"><spring:message code="menu.assets"/><span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li class="dropdown-item"><a href="../asset/add"><spring:message code="menu.create"/></a></li>
					<li class="dropdown-item"><a class="dropdown-item"
						href="../asset/update"><spring:message code="menu.update"/></a></li>
					<li class="dropdown-divider"></li>
					<li class="dropdown-item"><a class="dropdown-item"
						href="../asset/record"><spring:message code="menu.search"/></a></li>
				</ul></li>
			<li class="nav-item"><a class="nav-link" href="../area/home"><spring:message code="menu.areas"/></a>
			</li>
			<li class="nav-item"><a class="nav-link" href="../person/home"><spring:message code="menu.persons"/></a>
			</li>
		</ul>
	</div>
</nav>