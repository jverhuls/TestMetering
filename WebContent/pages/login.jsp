<jsp:include page="navigation.jsp">
	<jsp:param name="rightNavUrl" value="login(getValue('username'), getValue('password'))"/>
	<jsp:param name="rightNavTitle" value="Log in"/>
</jsp:include>
<div>
<span id="errorLogin" class="graytitle" style="visibility:hidden"></span>
<span class="graytitle">Log in met je account:</span><br>
<form id="theForm" onsubmit="login(getValue('username'), getValue('password'));return false;">
<ul class="pageitem">
	<li class="bigfield"><input id="username" name="username" placeholder="Gebruikersnaam" type="text" /></li>
	<li class="bigfield"><input id="password" name="password" placeholder="Paswoord" type="password" /></li>
</ul>
<div id="submitDiv" style="visibility:hidden;">
	<input type="submit">
</div>
</form>
</div>
<br><br><br><br><br><br><br><br>
<jsp:include page="footer.jsp"/>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>