<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spr" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>



<spr:recruiter header1="List Associate">

		
 <div class="container-main">
		<h1 class="h3 mb-0 text-gray-800">Registered Qualifications</h1>


		<!-- Large modal -->
		<div class="w-100 ">
		
		
		
		</div>
		<br>
		<hr>
		


		<div class="card">

			<div class="card-header bg-primary text-dark">
				<!-- <h4 class="card-title ">Regular header</h4>
         -->
				<%-- <h1 class="h3 mb-0 text-gray-800">${sp.spName}</h1>
					<p class="category">Category subtitle</p> --%>
			</div>
			<div class="card-body">
				<div class="hscroll">
				
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Name</th>
							<th scope="col">Type</th>
							<th scope="col">Field</th>
							<th scope="col">Duration</th>
						</tr>
					</thead>
					<tbody>


						<c:forEach items="${qlist }" var="q">
							<tr>
								<th scope="row">${q.id}</th>
								<td>${q.name}</td>
								<td>${q.type.name}</td>
								<td>${q.fieldOfStudy}</td>
								<td>${q.length}</td>
								
							</tr>


						</c:forEach>
					</tbody>
				</table>



			</div>
			</div>
		</div>



	</div>










	<style>
.hscroll {
  overflow-x: auto; /* Horizontal */
}
</style>	

</spr:recruiter>