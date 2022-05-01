<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spr" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />



<spr:candidate header1="List Associate">


	<div class="container-main">
		<h1 class="h3 mb-0 text-gray-800">My Profile</h1>
		<%-- <c:forEach items="${slplist }" var="sp">
 --%>
		
		
		
		<c:if test="${candidate eq null }">
		
		
		
		
			<div class="card">

				<div class="card-header bg-primary text-dark">
					<!-- <h4 class="card-title ">Regular header</h4>
        			 -->
					<%-- <h1 class="h3 mb-0 text-gray-800">${sp.spName}</h1>
					<p class="category">Category subtitle</p> --%>
				</div>
				<div class="card-body">
	
						<form action="addcandidateinfo" method="post">
						<label for="inputCity">Name:</label>
						<div class="form-row">
							<div class="form-group col-md-4">
								<label for="inputEmail4">First Name</label> <input type="text"
									class="form-control" id="firstName" name="firstName">
							</div>
							<div class="form-group col-md-4">
								<label for="inputPassword4">Middle Name</label> <input
									type="text" class="form-control" id="middleName" name="middleName">
							</div>
							<div class="form-group col-md-4">
								<label for="inputPassword4">Last Name</label> <input
									type="text" class="form-control" id="lastName" name="lastName">
							</div>
						</div>
						<label for="inputCity">Address</label>
						<div class="form-row">
						
							<div class="form-group col-md-4">
								<label for="inputEmail4">Country</label> <input type="text"
									class="form-control" id="country" name="address.country">
							</div>
							<div class="form-group col-md-4">
								<label for="inputPassword4">State</label> <input
									type="text" class="form-control" id="state" name="address.state">
							</div>
							<div class="form-group col-md-4">
								<label for="inputPassword4">Municipalty</label> <input
									type="text" class="form-control" id="municipalty" name="address.municipalty">
							</div>
						</div>
						<label for="inputCity">Contact</label>
						<div class="form-row">
									
	
		
							<div class="form-group col-md-6">
								<label for="inputPassword4">Email</label> <input
									type="text" class="form-control" id="email" name="email">
							</div>
							<div class="form-group col-md-6">
								<label for="inputPassword4">Phone</label> <input
									type="text" class="form-control" id="phoneNo" name="phoneNo">
							</div>
							
						</div>
						
						<div class="form-row">
						
							<div class="form-group col-md-3">
								<label for="inputCity">Age</label> <input type="number"
									class="form-control" id="age" name="age">
							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						
						<button type="submit" class="btn btn-primary">Save my Profile </button>
						
						<!-- <button type="button" class="btn btn-primary  pull-right float-right" data-toggle="modal"
			data-target=".bd-example-modal-lg">Close</button> -->
			
					</form>
					   
				</div>
			</div>
</c:if>
<c:if test="${candidate ne null }">
		
			<div class="card">

				<div class="card-header bg-primary text-dark">
					<!-- <h4 class="card-title ">Regular header</h4>
        			 -->
					<%-- <h1 class="h3 mb-0 text-gray-800">${sp.spName}</h1>
					<p class="category">Category subtitle</p> --%>
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
						<div class="col">${candidate.address.municipalty}-Municipalty,State-${candidate.address.state},${candidate.address.country}</div>
						
					</div>
					
					<div class="row">
						<div class="col"><a href="/">Applied Selection Process</a></div>
						
					</div>
					<div class="row">
						<div class="col"><a href="myqualifications">My certificates And Qualifications</a></div>
						
					</div>
					<div class="row">
						<div class="col"><a href="myskills">My Skills</a></div>
						
					</div>


					
					   
				</div>
			</div>
</c:if>
		<%-- </c:forEach>
 --%>
	</div>


	<!-- 
<div class="card  bg-light mb-3" style="max-width: 18rem;">
  <div class="card-header bg-primary text-dark">Header</div>
  <div class="card-body ">
    <h5 class="card-title ">Primary card title</h5>
    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
  </div>
</div> -->












</spr:candidate>