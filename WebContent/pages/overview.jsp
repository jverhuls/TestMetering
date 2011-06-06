<jsp:include page="navigation.jsp">
	<jsp:param name="title" value="off"/>
	<jsp:param name="leftNavUrl" value="logout();"/>
	<jsp:param name="leftNavTitle" value="Log uit"/>
	<jsp:param name="rightNavUrl" value="transform('OverviewPage','CustomerDetailPage')"/>
	<jsp:param name="rightNavTitle" value="Mijn gegevens"/>
</jsp:include>
<div>
 <img id="networkStatus" style="right:5px;z-index:-1;position:absolute" height="23" width="30">
</div>
<div align="center">
	<img id="chart"/>	
</div>
	<br>
	<div style="-webkit-border-radius: 10px; height:50px;color:white;text-align: center;line-height: 50px;-webkit-border-image: url('images/navbutton.png') 0 5 0 5;border-width: 0 5px;width:70%;margin-left:15%" onmousedown="transform('OverviewPage','InputPage')">Geef meterstand in</div> <br>
	<div style="-webkit-border-radius: 10px; height:50px;color:white;text-align: center;line-height: 50px;-webkit-border-image: url('images/navbutton.png') 0 5 0 5;border-width: 0 5px;width:70%;margin-left:15%" onmousedown="transform('OverviewPage','HistoryPage')">Historiek meterstand</div><br>	
	<div style="-webkit-border-radius: 10px; height:50px;color:white;text-align: center;line-height: 50px;-webkit-border-image: url('images/navbutton.png') 0 5 0 5;border-width: 0 5px;width:70%;margin-left:15%"><a href="mailto:info@capgemini.com?cc=helpdesk@capgemini.com&amp;subject=Meterstand van ${customer.name} ${customer.firstName} &amp;body=Beste,<br>Ik zou graag volgende meterstand willen ingeven:" style="color:white;text-decoration: none;">Email gegevens</a></div>
	<div id="iphoneOnly">
	    <br>
		<div style="-webkit-border-radius: 10px; height:50px;color:white;text-align: center;line-height: 50px;-webkit-border-image: url('images/navbutton.png') 0 5 0 5;border-width: 0 5px;width:70%;margin-left:15%"><a href="sms:1212" style="color:white;text-decoration: none;">Neem contact op per sms</a></div>
		<br>
		<div style="-webkit-border-radius: 10px; height:50px;color:white;text-align: center;line-height: 50px;-webkit-border-image: url('images/navbutton.png') 0 5 0 5;border-width: 0 5px;width:70%;margin-left:15%"><a href="tel:02 252 45 54" style="color:white;text-decoration: none;">Neem contact op per telefoon</a></div>
	</div>
	<jsp:include page="footer.jsp"/>