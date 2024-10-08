<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<body>
	<c:choose>
		<c:when test="${loginstate==true}">

			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand" href="#">WebSiteName</a>
					</div>
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Home</a></li>
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">Page 1-1</a></li>
								<li><a href="#">Page 1-2</a></li>
								<li><a href="#">Page 1-3</a></li>
							</ul></li>
						<li><a href="#">Page 2</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="myinfo"><span
								class="glyphicon glyphicon-user"></span> 개인정보</a></li>
						<li><a href="logout"><span
								class="glyphicon glyphicon-log-in"></span> 로그아웃</a></li>
					</ul>
				</div>
			</nav>
			<div class="container"></div>
		</c:when>

		<c:otherwise>
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand" href="#">WebSiteName</a>
					</div>
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Home</a></li>
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">Page 1-1</a></li>
								<li><a href="#">Page 1-2</a></li>
								<li><a href="#">Page 1-3</a></li>
							</ul></li>
						<li><a href="#">Page 2</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="memberinput"><span
								class="glyphicon glyphicon-user"></span> 회원가입</a></li>
						<li><a href="myinfo"><span
								class="glyphicon glyphicon-user"></span> 개인정보</a></li>
						<li><a href="login"><span
								class="glyphicon glyphicon-log-in"></span> 로그인</a></li>
					</ul>
				</div>
			</nav>
			<div class="container"></div>
		</c:otherwise>
	</c:choose>
</body>
</html>