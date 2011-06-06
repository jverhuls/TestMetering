
<jsp:include page="navigation.jsp">
	<jsp:param name="leftNavUrl" value="transform('CustomerDetailPage','OverviewPage',true)"/>
	<jsp:param name="leftNavTitle" value="Overzicht"/>
</jsp:include>

<span class="graytitle">Persoonlijke Gegevens:</span>
  <ul class="pageitem">
	<li class="smallfield"><span class="name">Naam:</span><div>Verhulst</div></li>
	<li class="smallfield"><span class="name">Voornaam:</span><div>Jan</div></li>
	<li class="smallfield"><span class="name">Email:</span><input type="email" name="email" value="verhulstjan@gmail.com"/></li>
	<li class="smallfield"><span class="name">Postcode:</span><div><b>2000</b></div></li>
	<li class="smallfield"><span class="name">Gemeente:</span><input type="text" name="city" value="Antwerpen"/></li>
  </ul>

<span class="graytitle">Warmwaterbereiding:</span>
	<ul class="pageitem">
			<li class="select"><select name="water">
			<option value="1">Electriciteit</option>
			<option value="2">Gas</option>
			<option value="3">Stookolie</option>
			</select><span class="arrow"></span> </li>
		</ul>
		
<span class="graytitle">Centrale verwarming:</span>
	<ul class="pageitem">
			<li class="select"><select name="heating">
			<option value="1">Electriciteit</option>
			<option value="2" selected>Gas</option>
			<option value="3">Stookolie</option>
			</select><span class="arrow"></span> </li>
		</ul>
		
<span class="graytitle">Om te koken gebruik ik:</span>
	<ul class="pageitem">
			<li class="select"><select name="cook">
			<option value="1">Electriciteit</option>
			<option value="2">Gas</option>
			<option value="3">Stookolie</option>
			</select><span class="arrow"></span> </li>
		</ul>

<span class="graytitle">Samenstelling gezin:</span>
<ul class="pageitem">
	<li class="smallfield"><span class="name">Aantal Personen:</span><input style="width:20px;text-align: right;right:115px;" type="number" name="persons" value="2"/><div style="right:80px; width:30px;text-align: right;">perso(o)n(en)&nbsp;</div></li>
</ul>

<span class="graytitle">Type woning:</span>
	<ul class="pageitem">
			<li class="select"><select name="cook">
			<option value="1">Appartement</option>
			<option value="2">Rijwoning</option>
			<option value="3">Eengezinswoning</option>
			</select><span class="arrow"></span> </li>
		</ul>

<span class="graytitle">Oppervlaktes:</span>
<ul class="pageitem">
	<li class="smallfield" onclick="setCursorPosition(document.getElementById('surface1'))"><span class="name" style="width:70%">Woonkamer en keuken:</span><input style="width:20%;text-align: right;right:30px" type="number" id="surface1" value="2000" onchange="calculateTotal()" onmousedown="setCursorPosition(this)"/><div style="right:0; width:10%;text-align: right;">m²&nbsp;</div></li>
	<li class="smallfield" onclick="setCursorPosition(document.getElementById('surface2'))"><span class="name" style="width:70%">Badkamer:</span><input style="width:20%;text-align: right;right:30px" type="number" id="surface2" onchange="calculateTotal()" value="2"/><div style="right:0; width:10%;text-align: right;">m²&nbsp;</div></li>
	<li class="smallfield" onclick="setCursorPosition(document.getElementById('surface3'))"><span class="name" style="width:70%">Slaapkamer(s):</span><input style="width:20%;text-align: right;right:30px" type="number" id="surface3" onchange="calculateTotal()" value="2"/><div style="right:0; width:10%;text-align: right;">m²&nbsp;</div></li>
	<li class="smallfield" onclick="setCursorPosition(document.getElementById('surface4'))"><span class="name" style="width:70%">Hal en gang:</span><input style="width:20%;text-align: right;right:30px" type="number" id="surface4" onchange="calculateTotal()" value="2"/><div style="right:0; width:10%;text-align: right;">m²&nbsp;</div></li>		
	<li class="smallfield" onclick="setCursorPosition(document.getElementById('surface5'))"><span class="name" style="width:70%">Overige kamers:</span><input style="width:20%;text-align: right;right:30px" type="number" id="surface5" onchange="calculateTotal()" value="2"/><div style="right:0; width:10%;text-align: right;">m²&nbsp;</div></li>
	<li class="smallfield" onclick="setCursorPosition(document.getElementById('surface6'))"><span class="name" style="width:70%">Totaal:</span><div style="right:10; width:30%;text-align: right;" id="totalSurface">1203&nbsp;&nbsp;m²&nbsp;</div></li>
</ul>

<script>

function calculateTotal(){
	var surface1 = document.getElementById('surface1').value*1;
	var surface2 = document.getElementById('surface2').value*1;
	var surface3 = document.getElementById('surface3').value*1;
	var surface4 = document.getElementById('surface4').value*1;
	var surface5 = document.getElementById('surface5').value*1;
	var total = surface1+surface2+surface3+surface4+surface5;
	document.getElementById('totalSurface').innerHTML = total+ "&nbsp;&nbsp;m²&nbsp;";
}

function setCursorPosition(field){
	field.selectionStart = field.value.length;
	field.selectionEnd = field.value.length;
}

</script>

<span class="graytitle">Mijn jaarverbruik:</span>
<ul class="pageitem">
	<li class="smallfield" onclick="setCursorPosition(document.getElementById('from'))"><span class="name" style="width:70%">Period van(dd/mm/jjjj):</span><div style="width:30%;text-align: right;right:5px; font-weight:bold;color:black;" id="from" onclick='inputDate(this)'>01/01/2009</div></li>
	<li class="smallfield" onclick="setCursorPosition(document.getElementById('till'))"><span class="name" style="width:70%">Period tot(dd/mm/jjjj):</span><div style="width:30%;text-align: right;right:5px; font-weight:bold;color:black;" id="from" onclick='inputDate(this)'>31/12/2009</div></li>
	<li class="smallfield" onclick="setCursorPosition(document.getElementById('consumption'))"><span class="name" style="width:70%">Verbruik:</span><input style="width:20%;text-align: right;right:40px" type="number" id="consumption" value="2123"/><div style="right:0; width:15%;text-align: right;">kWh&nbsp;</div></li>
</ul>

<span class="graytitle">Voorkeuren:</span>
		<ul class="pageitem">
			<li class="checkbox"><span class="name">Herinnering via SMS:</span><input name="remember" type="checkbox" /> </li>
			<li class="checkbox"><span class="check"><span class="name">Herinnering via mail:</span><input name="remember" type="checkbox" /></span></li>
		</ul>
<br>
<div style="-webkit-border-radius: 10px; height:50px;color:white;text-align: center;line-height: 50px;-webkit-border-image: url('images/navbutton.png') 0 5 0 5;border-width: 0 5px;width:90%;margin-left:4%" onmousedown="javascript:(window.location='customer?dispatch=usage');">Opslaan</div>
<jsp:include page="footer.jsp"/>
