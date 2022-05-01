<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" >
<%@tag import="java.util.Calendar"%>
<%@ tag body-content="scriptless"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spr" tagdir="/WEB-INF/tags"%>
<%@tag import="org.slf4j.Logger"%>
<%@tag import="org.slf4j.LoggerFactory"%>
<%@tag import="org.slf4j.Logger"%>
<%@ attribute name="header1" required="true" type="java.lang.String"%>
<c:set var="userType" scope="page"
	value="${AuthUtil.getCurrentUser().userType}" />
<c:set var="userName" scope="page"
	value="${AuthUtil.getCurrentUser().username}" />
<c:set var="BASE_URL" value="${pageContext.request.contextPath}" />



<html lang="en">
<head>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
		</head>
<body class="modal-backdrop-fade" ng-app="myAdminwallet"
	ng-controller="myAdmincontroller" ng-cloak>


	<div>
		<input type="hidden" name="${_csrf.parameterName}" id="csrfcode"
			value="${_csrf.token}" />
	</div>
	</div>
	</div>
	<!-- END OF CONTAINER MAIN -->
	<!-- -start div -->
	<div class="container-main bg-white" style="margin-left: 270px">
		
	</div>
	<!-- End Div Here -->
	
	<div class="clearfix"></div>

	<div>


		<jsp:doBody />

	</div>

	<div class="modal fade" id="mytransfer" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Fund Transfer</h4>
				</div>
				<div class="modal-body">

					<input type="hidden" ng-model="peerToPeerConfirm"
						ng-init="peerToPeer.confirmation = false">
						<div class="row">
							<div class="col-lg-12">

								<div class="col-lg-12" ng-hide=peerToPeer.confirmation>
										
								</div>
								<div class="col-lg-12" ng-show=peerToPeer.confirmation>
									<div class="form-horizontal">
										<h4 class="text-default">Please Confirm all the details :</h4>
										<h4>{{p2pmessage}}</h4>
										<ul class="list-group">
											<li class="list-group-item"><b>PayTime ID (Email):xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx</b>
												{{p2pwalletid}}</li> {{peerToPeerTransformError.peerId}}
											
										</ul>
										<button type="submit" class="btn btn-warning"
											ng-click="peerTransfer()">Confirm</button>
										<button ng-click="peerToPeer.confirmation = false"
											class="btn btn-primary">Reset</button>
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
						</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	<!-- Modal -->
	<div id="simStart" class="modal fade" role="dialog">
		
	</div>
	




</body>



</html>