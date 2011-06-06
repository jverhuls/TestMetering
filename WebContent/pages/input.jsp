<jsp:include page="navigation.jsp">
	<jsp:param name="leftNavUrl" value="transform('InputPage','OverviewPage', true)"/>
	<jsp:param name="leftNavTitle" value="Terug"/>
</jsp:include>

<span class="graytitle">Datum Meteropneming:</span>
  <ul class="pageitem">
	<li class="smallfield"><span class="name" id="recordDateInput"></span><span style="position:absolute;right:5px;top:7px;-webkit-border-radius: 10px; height:30px;color:white;text-align: center;line-height: 30px;-webkit-border-image: url('images/navbutton.png') 0 5 0 5;border-width: 0 5px;width:50px;" onmousedown="inputDate(document.getElementById('recordDateInput'))">Wijzig</span></li>
  </ul>
	<span class="graytitle">Meterstand Piek (in kWh)</span>
	 <ul class="pageitem">
		 <li class="smallfield"><span class="name" id="peakInput">000000,00</span><span style="position:absolute;right:5px;top:7px;-webkit-border-radius: 10px; height:30px;color:white;text-align: center;line-height: 30px;-webkit-border-image: url('images/navbutton.png') 0 5 0 5;border-width: 0 5px;width:50px;" onmousedown="inputMeter(document.getElementById('peakInput'))">Wijzig</span></li>
	 </ul>
	 <span class="graytitle">Meterstand Daluren (in kWh)</span>
	 <ul class="pageitem">
	  	<li class="smallfield"><span class="name" id="offpeakInput">000000,00</span><span style="position:absolute;right:5px;top:7px;-webkit-border-radius: 10px; height:30px;color:white;text-align: center;line-height: 30px;-webkit-border-image: url('images/navbutton.png') 0 5 0 5;border-width: 0 5px;width:50px;" onmousedown="inputMeter(document.getElementById('offpeakInput'))">Wijzig</span></li>
	 </ul>
	 <span class="graytitle">Meterstand Exclusief nacht (in kWh)</span>
	 <ul class="pageitem">
	  	<li class="smallfield"><span class="name" id="xnightInput">000000,00</span><span style="position:absolute;right:5px;top:7px;-webkit-border-radius: 10px; height:30px;color:white;text-align: center;line-height: 30px;-webkit-border-image: url('images/navbutton.png') 0 5 0 5;border-width: 0 5px;width:50px;" onmousedown="inputMeter(document.getElementById('xnightInput'))">Wijzig</span></li>
	 </ul>
	<div style="-webkit-border-radius: 10px; height:50px;color:white;text-align: center;line-height: 50px;-webkit-border-image: url('images/navbutton.png') 0 5 0 5;border-width: 0 5px;width:90%;margin-left:4%" onmousedown="addMeterRecord();transform('InputPage','OverviewPage', true);">Opslaan</div>

<jsp:include page="footer.jsp"/>