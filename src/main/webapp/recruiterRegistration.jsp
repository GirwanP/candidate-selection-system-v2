<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Create an account</title>

      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
  </head>

  <body>

    <div class="container">

        <form:form method="POST" modelAttribute="recruiterForm" class="form-signin">
            <h2 class="form-signin-heading">Register a recruiter account</h2>
            <spring:bind path="companyName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="companyName" class="form-control" placeholder="Company Name"
                                autofocus="true"></form:input>
                    <form:errors path="companyName"></form:errors>
                </div>
            </spring:bind>

 <spring:bind path="panNo">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="panNo" class="form-control" placeholder="PAN no.:"
                                autofocus="true"></form:input>
                    <form:errors path="panNo"></form:errors>
                </div>
            </spring:bind>

 <spring:bind path="email">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="email" class="form-control" placeholder="Email as username"
                                autofocus="true"></form:input>
                    <form:errors path="email"></form:errors>
                </div>
            </spring:bind>

<spring:bind path="phoneNo">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="phoneNo" class="form-control" placeholder="Phone no:"
                                autofocus="true"></form:input>
                    <form:errors path="phoneNo"></form:errors>
                </div>
            </spring:bind>
<%-- <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                autofocus="true"></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </spring:bind> --%>
<spring:bind path="country">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="country" class="form-control" placeholder="Country"
                                autofocus="true"></form:input>
                    <form:errors path="country"></form:errors>
                </div>
            </spring:bind>
<spring:bind path="state">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="state" class="form-control" placeholder="State"
                                autofocus="true"></form:input>
                    <form:errors path="state"></form:errors>
                </div>
            </spring:bind>
<spring:bind path="municipalty">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="municipalty" class="form-control" placeholder="Municipalty"
                                autofocus="true"></form:input>
                    <form:errors path="municipalty"></form:errors>
                </div>
            </spring:bind>
            
            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                    <form:errors path="password"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="passwordConfirm">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="password" path="passwordConfirm" class="form-control"
                                placeholder="Confirm your password"></form:input>
                    <form:errors path="passwordConfirm"></form:errors>
                </div>
            </spring:bind>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>

    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
  </body>
</html>
