<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spr" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>



<spr:recruiter header1="List Associate">

		
  <div class="container-main">
  <h1 class="h3 mb-0 text-gray-800">JOb Opening Detail </h1>
  
  
    <div class="card">
      <div class="card-header bg-primary text-dark">
        <!-- <h4 class="card-title ">Regular header</h4>
         -->
         <h1 class="h3 mb-0 text-gray-800"> </h1>
        <p class="category">--</p>
      </div>
      <div class="card-body">
      <div class="hscroll">
       	    	<table class="table">
  <thead>
   <!--  <tr>
      <th scope="col">#</th>
      <th scope="col">First</th>
      <th scope="col">Last</th>
      <th scope="col">Handle</th>
    </tr> -->
  </thead>
  
 
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Total Candidates</td>
      <td>${sp.totalCandidateCount}</td>
    </tr><tr>
      <th scope="row">2</th>
      <td>Selection Process Name</td>
      <td>${sp.name}</td>
    </tr><tr>
      <th scope="row">3</th>
      <td>Identifier</td>
      <td>${sp.uniqueId}</td>
    </tr>
    <tr>
      <th scope="row">4</th>
      <td>Opening date</td>
      <td>${sp.openDate}</td>
    </tr>
    <tr>
      <th scope="row">5</th>
      <td>Closing Date</td>
      <td>${sp.closingDate}</td>
    </tr>
    
  </tbody>
</table>
</div>
       	
      </div>
    </div>
    <hr/>
     <h1 class="h3 mb-0 text-gray-800">Qualification Links List </h1>
    <h4></h4>
    <button type="button" class="btn btn-primary  pull-right  " data-toggle="modal"
			data-target=".bd-example-modal-lg" >Add New Qualification Link</button>
     <div class="card">
     
    <div class="card-body">
				
				
				
				<!-- Large modal -->
		<div class="w-100 ">
		
		
		
		</div>
		<br>
		<hr>
		
				
				
				<div class="hscroll">
				<table class="table table-striped">
					<thead>
						<tr class="bg-primary font-weight-bolder text-white"	>
							<th scope="col">S.N</th>
							<th scope="col">Id</th>
							<th scope="col">Qualification Name</th>
							
							<th scope="col">Qualification id</th>
							<th scope="col">Evaluation Points</th>
							<th scope="col">Selection Process</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>


						<c:forEach items="${qll }" var="q" varStatus="loop">
							<tr>
								<td scope="row">${loop.index+1}</td>
								<th >${q.id}</th>
								<td>${q.qulificationName}</td>
								<td>${q.qualificationId}</td>
								<td>${q.points}</td>
								
								<td>${q.sPname}</td>
								<td><a href="removeqlink?qlinkid=${q.id}&spid=${sp.id}">Remove</a></td>

							</tr>

						</c:forEach>
					</tbody>
				</table>
</div>
			</div>
			</div>
    
  </div>
  


<hr/>
     <h1 class="h3 mb-0 text-gray-800">Skills Links List </h1>
    <h4></h4>
    <button type="button" class="btn btn-primary  pull-right  " data-toggle="modal"
			data-target=".bd-example-modal-lg2" >Add New Skill Link</button>
     <div class="card">
     
    <div class="card-body">
				
				
				
				<!-- Large modal -->
		<div class="w-100 ">
		
		
		
		</div>
		<br>
		<hr>
		
				
				
				<div class="hscroll">
				<table class="table table-striped">
					<thead>
						<tr class="bg-primary font-weight-bolder text-white"	>
							<th scope="col">S.N</th>
							<th scope="col">Id</th>
							<th scope="col">Skill Name</th>
							
							<th scope="col">Skill id</th>
							<th scope="col">Evaluation Points</th>
							<th scope="col">Selection Process</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>


						<c:forEach items="${sll }" var="q" varStatus="loop">
							<tr>
								<td scope="row">${loop.index+1}</td>
								<th >${q.id}</th>
								<td>${q.skillName}</td>
								<td>${q.skillId}</td>
								<td>${q.points}</td>
								
								<td>${q.sPname}</td>
								<td><a href="removeslink?qlinkid=${q.id}&spid=${sp.id}">Remove</a></td>

							</tr>

						</c:forEach>
					</tbody>
				</table>
</div>
			</div>
			</div>
    
  
  
  
  
  
  <div class="modal fade bd-example-modal-lg" tabindex="-1"
			role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					
					<div class="card">
					<div class="card-body">
					
					
							
					<form action="addqlink" method="post">
					<label class="text-dark font-weight-bold">Add new Qualification Link to ${sp.name } </label> 
						
						<div class="form-row">
							<div class="form-group col-md-8">
								<label >Qualification Entry </label> 
									
									<select class="form-control" id="type" placeholder="fsdf" name="qualificationId">
									<option disabled="disabled" selected="selected">---select qualification type</option>
									<c:forEach items="${qlist }" var="qt">
										<option  value="${qt.id }">${qt.name}</option>
									</c:forEach>
									</select>
							</div>
							
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="inputEmail4">Points For Qualification</label> <input type="number"
									class="form-control" id="points" name="points">
							</div>
							
						</div>
						
						<input type="hidden" value="${sp.id}" name="spid">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						
						<button type="submit" class="btn btn-primary">Add Qualifications Link</button>
						
						<button type="button" class="btn btn-primary  pull-right float-right" data-toggle="modal"
			data-target=".bd-example-modal-lg">Close</button>
					</form>

					</div>
					</div>
				</div>
			</div>
		</div>
		
		
		
		<div class="modal fade bd-example-modal-lg2" tabindex="-1"
			role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					
					<div class="card">
					<div class="card-body">
					
					
							
					<form action="addslink" method="post">
					<label class="text-dark font-weight-bold">Add new Skill Link to ${sp.name } </label> 
						
						<div class="form-row">
							<div class="form-group col-md-8">
								<label >Skill Entry </label> 
									
									<select class="form-control" id="type" placeholder="fsdf" name="skillId">
									<option disabled="disabled" selected="selected">select skill type</option>
									<c:forEach items="${slist }" var="qt">
										<option  value="${qt.id }">${qt.skillName}</option>
									</c:forEach>
									</select>
							</div>
							
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="inputEmail4">Points For Skill</label> <input type="number"
									class="form-control" id="points" name="points">
							</div>
							
						</div>
						
						<input type="hidden" value="${sp.id}" name="spid">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						
						<button type="submit" class="btn btn-primary">Add Skill Link</button>
						
						<button type="button" class="btn btn-primary  pull-right float-right" data-toggle="modal"
			data-target=".bd-example-modal-lg">Close</button>
					</form>

					</div>
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