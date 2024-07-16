<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<h3 class="pt-3 pb-3">Report Generation</h3>

		<form:form action="search" modelAttribute="searchObj" method="post">

			<table>
				<tr>
					<td>Plan Name :</td>
					<td><form:select path="planName">
							<form:option value="">-Select-</form:option>
							<form:options items="${planNames}" />
						</form:select></td>
					<td>Plan Status :</td>
					<td><form:select path="planStatus">
							<form:option value="">-Select-</form:option>
							<form:options items="${planStatuses}" />
						</form:select></td>
					<td>Gender :</td>
					<td><form:select path="gender">
							<form:option value="">-Select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Fe-Male">Fe-Male</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>Start Date :</td>
					<td><form:input path="startDate" type="date"
							data-date-type="yyyy-mm-dd" /></td>
					<td>End Date :</td>
					<td><form:input path="endDate" type="date"
							data-date-type="yyyy-mm-dd" /></td>
				</tr>
				<tr>
					<td><button class="btn btn-primary mt-3" value="Submit"><b>Search</b></button></td>
					<td><a href="/" class="btn btn-secondary mt-3"><b>Reset</b></a></td>
				</tr>
			</table>

		</form:form>

		<hr>

		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>Sl No.</th>
					<th>Holder Name</th>
					<th>Gender</th>
					<th>Plan Name</th>
					<th>Plan Status</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Benefit Amount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${plans}" var="plan" varStatus="index">
					<tr>
						<td>${index.count}</td>
						<td>${plan.citizenName}</td>
						<td>${plan.gender}</td>
						<td>${plan.planName}</td>
						<td>${plan.planStatus}</td>
						<td>${plan.startDate}</td>
						<td>${plan.endDate}</td>
						<td>${plan.benifitAmt}</td>
					</tr>
				</c:forEach>

				<tr>
					<c:if test="${empty plans}">
						<td colspan="8"><p class="text-danger text-center pt-3">No Records Found !!!</p></td>
					</c:if>
				</tr>
			</tbody>
		</table>


		<hr>

		<h6>
			Export : <a href="excel">Excel</a> <a href="pdf">Pdf</a>
		</h6>

	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>