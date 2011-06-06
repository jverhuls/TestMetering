<jsp:include page="navigation.jsp">
	<jsp:param name="title" value="off"/>
	<jsp:param name="leftNavUrl" value="transform('HistoryDetailPage','HistoryPage',true)"/>
	<jsp:param name="leftNavTitle" value="Historiek"/>
</jsp:include>

<span class="graytitle">Datum Meteropneming:</span>
  <ul class="pageitem">
	<li class="smallfield"><span class="name" id="recordDateRO">01/01/2010</span></li>
  </ul>
	<span class="graytitle">Meterstand Piek (in kWh)</span>
	 <ul class="pageitem">
		 <li class="smallfield"><span class="name" id="peakRO">001234,22</span></li>
	 </ul>
	 <span class="graytitle">Meterstand Daluren (in kWh)</span>
	 <ul class="pageitem">
	  	<li class="smallfield"><span class="name" id="offpeakRO">004238,75</span></li>
	 </ul>
	 <span class="graytitle">Meterstand Exclusief nacht (in kWh)</span>
	 <ul class="pageitem">
	  	<li class="smallfield"><span class="name" id="xnightRO">000000,00</span></li>
	 </ul>
	 
	 <div style="-webkit-border-radius: 10px; height:50px;color:white;text-align: center;line-height: 50px;-webkit-border-image: url('images/navbuttonred.png') 0 5 0 5;border-width: 0 5px;width:90%;margin-left:4%" onmousedown="deleteMeterRecord()">Verwijder</div>
<jsp:include page="footer.jsp"/>