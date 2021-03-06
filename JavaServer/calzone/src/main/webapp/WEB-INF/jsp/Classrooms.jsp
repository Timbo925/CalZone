<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>CalZone</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css"
	media="screen">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootswatch.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/calzone.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/profile.css">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="${pageContext.request.contextPath}/js/html5shiv.js"></script>
      <script src="${pageContext.request.contextPath}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<script src="${pageContext.request.contextPath}/js/bsa.js"></script>

	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a href="${pageContext.request.contextPath}/" class="navbar-brand"><spring:message
						code="navbar.calzone.text" /></a>
				<button class="navbar-toggle" type="button" data-toggle="collapse"
					data-target="#navbar-main">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="navbar-collapse collapse" id="navbar-main">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}"><spring:message
								code="navbar.home.text" /></a></li>
					<li><a><spring:message code="navbar.account.text" /></a></li>
					<li><a><spring:message code="navbar.courses.text" /></a></li>
					<li><a href="${pageContext.request.contextPath}/hello/"><spring:message
								code="navbar.calendar.text" /></a></li>
					<li><a><spring:message code="navbar.help.text" /></a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="?lang=en">English</a></li>
					<li><a href="?lang=nl">Nederlands</a></li>
					<li><a href="<c:url value='j_spring_security_logout' />"><spring:message
								code="navbar.logout.text" /></a></li>
				</ul>

			</div>
		</div>
	</div>

	<div class="container">

		<div class="row">
			<div class="col-lg-12">
				<div class="page-header">
					<h1 id="type">
						<spring:message code="classrooms.title.text" />
						<!-- Button trigger modal -->
  						<a data-toggle="modal" href="${pageContext.request.contextPath}/#addNewClassroom" class="btn btn-primary">+ <spring:message code="classroom.new.text"/></a>
					</h1>
				</div>
			</div>
		</div>
		
		<!-- Modal -->
		<div class="modal fade" id="addNewClassroom" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title"><spring:message code="classroom.new.text"/></h4>
					</div>
					<form:form commandName="room" class="bs-example form-horizontal">
						<div class="modal-body">
							<fieldset>
								<div class="form-group">
									<div class="col-lg-12">
										<spring:message code="classrooms.building.text" var="buildingPlaceholderText"/>
										<form:input path="building" class="form-control" placeholder="${buildingPlaceholderText}"/>
										<form:errors path="building" cssClass="error"></form:errors>
									</div>
									<br>
									<div class="col-lg-12">
										<spring:message code="classrooms.floor.text" var="floorPlaceholderText"/>
										<form:input path="floor" class="form-control" placeholder="${floorPlaceholderText}"/>
										<form:errors path="floor" cssClass="error"></form:errors>
									</div>
									<br>
									<div class="col-lg-12">
										<spring:message code="classrooms.name.text" var="namePlaceholderText"/>
										<form:input path="name" class="form-control" placeholder="${namePlaceholderText}"/>
										<form:errors path="name" cssClass="error"></form:errors>
									</div>
									<br>
									<div class="col-lg-12">
										<spring:message code="classrooms.capacity.text" var="capacityPlaceholderText"/>
										<form:input path="capacity" class="form-control" placeholder="${capacityPlaceholderText}"/>
										<form:errors path="capacity" cssClass="error"></form:errors>
									</div>
									<br>
									<div class="col-lg-12">
										<spring:message code="classrooms.hasProjector.text" var="projectorPlaceholderText"/>
										<form:checkbox path="projectorEquipped" class="" label="${projectorPlaceholderText}"/>
									</div>
									<div class="col-lg-12">
										<spring:message code="classrooms.hasSMARTBoard.text" var="SMARTBoardPlaceholderText"/>
										<form:checkbox path="smartBoardEquipped" class="" label="${SMARTBoardPlaceholderText}"/>
									</div>
									<div class="col-lg-12">
										<spring:message code="classrooms.hasRecorder.text" var="recorderPlaceholderText"/>
										<form:checkbox path="recorderEquipped" class="" label="${recorderPlaceholderText}"/>
									</div>
								</div>
							</fieldset>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="general.close.text"/></button>
							<button type="submit" class="btn btn-primary" 
									data-loading-text=<spring:message code="general.working.text"/>><spring:message code="general.add.text"/></button>
						</div>
					</form:form>
				</div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
		
		<div class="row">
			<div class="col-lg-12">
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th><spring:message code="classrooms.room.text"/></th>
								<th><spring:message code="classrooms.capacity.text"/></th>
								<th><spring:message code="classrooms.projector.text"/></th>
								<th><spring:message code="classrooms.smartboard.text"/></th>
								<th><spring:message code="classrooms.recording.text"/></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${classroomArrayList}" var="room">
							    <tr>
							        <td>${room.classroomVUBNotation}</td>
							        <td>${room.capacity}</td>
							        <td>${room.projectorEquipped}</td>
							        <td>${room.smartBoardEquipped}</td>
							        <td>${room.recorderEquipped}</td>
							    </tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<script
			src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/bootswatch.js"></script>
</body>
</html>