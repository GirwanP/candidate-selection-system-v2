<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spr" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>



<spr:recruiter header1="List Associate">

		
 <div class="container-main">
		<h1 class="h3 mb-0 text-gray-800">Registered Skills</h1>


		<!-- Large modal -->
		<div class="w-100 ">
		
		
		
		</div>
		<br>
		<hr>
		


		<div class="card">

			<div class="card-header bg-primary text-dark">
				
			</div>
			<div class="card-body">
				<div class="hscroll">
				
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Name</th>
							<th scope="col">Level</th>
							
						</tr>
					</thead>
					<tbody>


						<c:forEach items="${qlist }" var="q">
							<tr>
								<th scope="row">${q.id}</th>
								<td>${q.skillName}</td>
								<td>${q.skillLevel.name}</td>
								

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