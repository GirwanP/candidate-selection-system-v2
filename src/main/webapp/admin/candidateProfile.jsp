<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spr" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />



<spr:admin header1="List Associate">


	<div class="container-main">
		<h1 class="h3 mb-0 text-gray-800">Candidate Profile</h1>


			<div class="card">

				<div class="card-header bg-primary text-dark">
					<!-- <h4 class="card-title ">Regular header</h4>
         -->
					<h1 class="h3 mb-0 text-gray-800">${sp.spName}</h1>
					<p class="category">Category subtitle</p>
				</div>
				<div class="card-body">

					<div class="row">
						<div class="col">First Name</div>
						<div class="col">${candidate.firstName }</div>
						
					</div>
					<div class="row">
						<div class="col">Middle Name</div>
						<div class="col">${candidate.middleName}</div>
						
					</div><div class="row">
						<div class="col">Last Name</div>
						<div class="col">${candidate.lastName }</div>
						
					</div><div class="row">
						<div class="col">Age</div>
						<div class="col">${candidate.age }</div>
						
					</div>
					<div class="row">
						<div class="col">Email</div>
						<div class="col">${candidate.email}</div>
						
					</div>
					<div class="row">
						<div class="col">Phone</div>
						<div class="col">${candidate.phoneNo }</div>
						
					</div>
					
					<div class="row">
						<div class="col">Address</div>
						<div class="col">${candidate.address.municipalty}Municipalty,State-${candidate.address.state},${candidate.address.country}</div>
						
					</div>
					
					<div class="row">
						<div class="col"><a >Applied Selection Process</a></div>
						
					</div>
					<div class="row">
						<div class="col"><a>My certificates And Qualifications</a></div>
						
					</div>


					
					 
				</div>
			</div>

		

	</div>


	











</spr:admin>