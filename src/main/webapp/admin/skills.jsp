<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spr" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>



<spr:admin header1="List Associate">

		
 <div class="container-main">
		<h1 class="h3 mb-0 text-gray-800">Registered Skills</h1>


		<!-- Large modal -->
		<div class="w-100 ">
		
		<button type="button" class="btn btn-primary  pull-right float-right" data-toggle="modal"
			data-target=".bd-example-modal-lg">Register New Skill</button>
		
		</div>
		<br>
		<hr>
		<div class="modal fade bd-example-modal-lg" tabindex="-1"
			role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					
					<div class="card">
					<div class="card-body">
					
					<label class="text-dark font-weight-bold">Register new Skill  </label> 
						
							
					<form action="addskills" method="post">
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="inputEmail4">Skill Name</label> <input type="text"
									class="form-control" id="skillName" name="skillName">
							</div>
							
						</div>
						
						<div class="form-row">
							<div class="form-group col-md-8">
								<label >Skill Level</label> 
									
									<select class="form-control" id="type" placeholder="fsdf" name="skillLevelId">
									<option disabled="disabled" selected="selected">--select skill level--</option>
									<c:forEach items="${qtlist }" var="qt">
										<option  value="${qt.id }">${qt.name}</option>
									</c:forEach>
									</select>
							</div>
							
						</div>
						
						
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						
						<button type="submit" class="btn btn-primary">Add Skill </button>
						
						<button type="button" class="btn btn-primary  pull-right float-right" data-toggle="modal"
			data-target=".bd-example-modal-lg">Close</button>
					</form>

					</div>
					</div>
				</div>
			</div>
		</div>


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
							<th scope="col">Level</th>
							<th scope="col">Action</th>
							
						</tr>
					</thead>
					<tbody>


						<c:forEach items="${qlist }" var="q">
							<tr>
								<th scope="row">${q.id}</th>
								<td>${q.skillName}</td>
								<td>${q.skillLevel.name}</td>
								<td> 
									<a href="removeskill?qid=${q.id}">Invalidate Skill</a>
								</td>
								

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

</spr:admin>