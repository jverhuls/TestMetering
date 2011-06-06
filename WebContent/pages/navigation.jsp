 <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
 
<div id="topbar">
<c:if test="${!empty param['leftNavUrl'] }">
	<c:if test="${!empty param['leftNavUrl'] }">
<div id="leftnav" style="line-height: 30px">
		<div onmousedown="${param['leftNavUrl']}">${param['leftNavTitle']}</div>
		</div>
	</c:if>

</c:if>

<c:if test="${param['title'] != 'off'}">
	<div class="navText">Mijn Verbruik</div>
</c:if>
<c:if test="${!empty param['rightNavUrl']}">
	<div id="rightnav" style="line-height: 30px">
		<div onmousedown="${param['rightNavUrl']}">${param['rightNavTitle']}</div></div>
</c:if>
</div>