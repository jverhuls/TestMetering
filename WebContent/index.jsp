<!DOCTYPE html>
 <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 
<html manifest="cache.manifest">

<head>
	<jsp:include page="pages/header.jsp"/>
	<script>
		function load(){
			console.log('load body');
			console.log(window.localStorage.getItem('userid'));
			if(window.localStorage.getItem('userid') != null){
				document.getElementById('LogonPage').className="page";
				document.getElementById('OverviewPage').className="current";
				initOverviewPage();
			}else{
				document.getElementById('LogonPage').className="current";
			}
		}
	</script>
</head>
<body onload="load()">

<div id="LogonPage" class="page">
	<jsp:include page="pages/login.jsp"/>
</div>
<div id="OverviewPage" class="page">
	<jsp:include page="pages/overview.jsp"/>
</div>
<div id="CustomerDetailPage" class="page">
	<jsp:include page="pages/customerDetail.jsp"/>
</div>
<div id="InputPage" class="page">
	<jsp:include page="pages/input.jsp"/>
</div>
<div id="HistoryPage" class="page">
	<jsp:include page="pages/customerHistory.jsp"/>
</div>
<div id="HistoryDetailPage" class="page">
	<jsp:include page="pages/customerHistoryDetail.jsp"/>
</div>

</body>

</html>