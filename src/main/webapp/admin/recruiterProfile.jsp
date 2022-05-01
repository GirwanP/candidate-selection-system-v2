<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spr" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />



<spr:admin header1="List Associate">


	<div class="container-main">
		<h1 class="h3 mb-0 text-gray-800">Recruiter Detail</h1>


			<div class="card">

				<div class="card-header bg-primary text-dark">
					<!-- <h4 class="card-title ">Regular header</h4>
         -->
					<h1 class="h3 mb-0 text-gray-800">${sp.spName}</h1>
					<p class="category">Delais of the company</p>
				</div>
				<div class="card-body">

					<div class="row">
						<div class="col">Agency Name</div>
						<div class="col">${candidate.companyName }</div>
						
					</div>
					<div class="row">
						<div class="col">Pan No;</div>
						<div class="col">${candidate.panNo}</div>
						
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
						<div class="col">${candidate.address.municipalty} Municipalty,State-${candidate.address.state},${candidate.address.country}</div>
						
					</div>
					
					<div class="row">
						<div class="col"><a >Open jobs</a></div>
						
					</div>
					

					
					 
				</div>
			</div>

		

	</div>


	











</spr:admin>