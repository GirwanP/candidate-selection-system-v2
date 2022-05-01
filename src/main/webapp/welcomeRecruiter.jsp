<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spr" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>



<spr:recruiter header1="List Associate">

		
  <div class="container-main">
  <h1 class="h3 mb-0 text-gray-800">My Evaluations </h1>
  <c:forEach items="${slplist }" var="sp">
  
  
    <div class="card">
      <div class="card-header bg-primary text-dark">
        <!-- <h4 class="card-title ">Regular header</h4>
         -->name
         <h1 class="h3 mb-0 text-gray-800">${sp.name} </h1>
        <p class="category">Category subtitle</p>
      </div>
      <div class="card-body">
       	
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
      <td>My points</td>
      <td>${sp.score}</td>
    </tr><tr>
      <th scope="row">1</th>
      <td>My rank</td>
      <td>${sp.rank}</td>
    
    
  </tbody>
</table>
       	
      </div>
    </div>
    
   </c:forEach>
    
  </div>


	

</spr:recruiter>