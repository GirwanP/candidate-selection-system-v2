<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spr" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>



<spr:recruiter header1="List Associate">

		
  <div class="container-main">
  <h1 class="h3 mb-0 text-gray-800">JOB </h1>
  
  
    <div class="card">
      <div class="card-header bg-primary text-dark">
        <!-- <h4 class="card-title ">Regular header</h4>
         -->
         <h1 class="h3 mb-0 text-gray-800">${sp.name} </h1>
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
      <td>Job Name/Title</td>
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
    
    
  </div>



<div class="card  bg-light mb-3" ">
  <div class="card-header bg-primary text-dark">Rank Of Candidates</div>
  <div class="card-body ">
    <h5 class="card-title "></h5>
    <p class="card-text">*ranks are updated with each request</p>
 
 <div class="hscroll">
 	<table class="table">
  <thead>
     <tr>
      <th scope="col">#</th>
      <th scope="col">Id</th>
      <th scope="col">Candidate Name</th>
      <th scope="col">Candidate Id</th>
      <th scope="col">Points</th>
      <th scope="col">Rank</th>
    </tr>
  </thead>

 
  <tbody>
  <c:forEach var="spl" items="${sprank }">
   <tr onclick="window.location='candidatedetail?cid=${spl.candidate.id }';">
      <th scope="row">1</th>
      <td>${spl.id }</td>
      <td>${spl.candidate.firstName} ${spl.candidate.middleName} ${spl.candidate.lastName} </td>
      <td>${spl.candidate.id }</td>
      <td>${spl.score }</td>
      <td>${spl.rank }</td>
    </tr>
  </c:forEach>
   
    
  </tbody>
</table>
 
 </div>
 
  </div>
</div>










		
<style>
.hscroll {
  overflow-x: auto; /* Horizontal */
}
</style>
</spr:recruiter>