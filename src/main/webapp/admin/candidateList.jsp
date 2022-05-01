<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spr" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>



<spr:admin header1="List Associate">

		
  <div class="container-main">
  <h1 class="h3 mb-0 text-gray-800">Candidates List </h1>
  
  
    <div class="card">
      <div class="card-header bg-primary text-dark">
        <!-- <h4 class="card-title ">Regular header</h4>
         -->
         <h1 class="h3 mb-0 text-gray-800">${sp.spName} </h1>
        <p class="category">list</p>
      </div>
      <div class="card-body">
       	<div class="hscroll">
       	<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Name</th>
							<th scope="col">Email</th>
							<th scope="col">Phone no</th>
							<th scope="col">Age</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					
					<tbody>


						<c:forEach items="${clist }" var="q">
							<tr>
								<th scope="row">${q.id}</th>
								<td>${q.firstName} ${q.middleName} ${q.lastName}</td>
								<td>${q.email}</td>
								<td>${q.phoneNo}</td>
								<td>${q.age}</td>
								<td>
								
								<c:if test="${q.active}">
									<a href="deactivatec?candidateid=${q.id}">Deactivate</a>
								</c:if>
								<c:if test="${not q.active}">
									<a href="activatec?candidateid=${q.id}">Activate</a>

								</c:if>
									<a href="candidatedetail?cid=${q.id}">Details</a>
								 
								 
								 </td>

							</tr>


						</c:forEach>
					</tbody>
				</table>
				</div>
       	
      </div>
    </div>
    
    
  </div>


<!-- 
<div class="card  bg-light mb-3" style="max-width: 18rem;">
  <div class="card-header bg-primary text-dark">Header</div>
  <div class="card-body ">
    <h5 class="card-title ">Primary card title</h5>
    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
  </div>
</div> -->





<style>
.hscroll {
  overflow-x: auto; /* Horizontal */
}
</style>




		

</spr:admin>