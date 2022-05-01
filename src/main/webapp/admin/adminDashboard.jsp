<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spr" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>



<spr:admin header1="List Associate">

		
  <div class="container-main">
  <h1 class="h3 mb-0 text-gray-800">Summary </h1>
  <c:forEach items="${slplist }" var="sp">
  
  
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
      <th scope="row">1</th>
      <td>Selection Process Name</td>
      <td>${sp.name}</td>
    </tr><tr>
      <th scope="row">1</th>
      <td>Identifier</td>
      <td>${sp.uniqueId}</td>
    </tr>
    <tr>
      <th scope="row">1</th>
      <td>Opening date</td>
      <td>${sp.openDate}</td>
    </tr>
    <tr>
      <th scope="row">1</th>
      <td>Closing Date</td>
      <td>${sp.closingDate}</td>
    </tr>
    
  </tbody>
</table>
</div>
       	<a href="spdetails?spid=${sp.id }">Selection Process Detail</a>
       	<a href="sprank?spid=${sp.id }">View complete Rank</a>
      </div>
    </div>
    
   </c:forEach>
    
  </div>





		
<style>
.hscroll {
  overflow-x: auto; /* Horizontal */
}
</style>
</spr:admin>