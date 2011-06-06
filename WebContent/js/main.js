var customer;
var db;
var detailId;

function initCustomer(jsonCustomer){
	customer = eval('(' + jsonCustomer +')');
	console.log('storing userid:' + customer.userid);
	 window.localStorage.setItem('userid',customer.userid);
	 saveCustomer();
}

function saveCustomer(){
	try{
		console.log('save customer');
	if(db == null){
		db = window.openDatabase("DBName", "1.0", "description", 5*1024*1024);
	}
	 if (db) {
		db.transaction(function(tx) {
			console.log('create tables');
			tx.executeSql('CREATE TABLE IF NOT EXISTS customer (id unique,userid TEXT, firstName TEXT, name TEXT, email TEXT, zipcode TEXT, water TEXT, heating TEXT, cook TEXT)');
			tx.executeSql('CREATE TABLE IF NOT EXISTS consumptionrecord (id, peak TEXT, offpeak TEXT, xnight TEXT, date TEXT)');
			tx.executeSql('CREATE TABLE IF NOT EXISTS expectedConsumptions (cust_id NUMBER, cons_id NUMBER)');
			tx.executeSql('CREATE TABLE IF NOT EXISTS historicalConsumptions (cust_id NUMBER, cons_id NUMBER)');
			console.log('delete tables');
			tx.executeSql('delete from customer');
			tx.executeSql('delete from consumptionrecord');
			tx.executeSql('delete from expectedConsumptions');
			tx.executeSql('delete from historicalConsumptions');

			
			console.log('inserting');
	        tx.executeSql('INSERT INTO customer (id, userid, firstName, name, email, zipcode, water, heating, cook) values (?, ?, ?, ?, ?, ?, ?,?, ?)', [customer.id, customer.userid, customer.firstName, customer.name, customer.email, customer.zipcode, customer.water, customer.heating, customer.cook]);
            for(var i=0; i< customer.expectedConsumptions.length; i++){
        	   tx.executeSql('INSERT INTO expectedConsumptions (cust_id, cons_id) values (?, ?)', [customer.id, customer.expectedConsumptions[i].id]);
        	   tx.executeSql('INSERT INTO consumptionrecord (id, peak, offpeak, xnight,date) values (?, ?, ?, ?, ?)', [customer.expectedConsumptions[i].id, customer.expectedConsumptions[i].peak, customer.expectedConsumptions[i].offpeak, customer.expectedConsumptions[i].xnight, customer.expectedConsumptions[i].date]);
            }
            for(var i=0; i< customer.historicalConsumptions.length; i++){
        	   tx.executeSql('INSERT INTO historicalConsumptions (cust_id, cons_id) values (?, ?)', [customer.id, customer.historicalConsumptions[i].id]);
        	   tx.executeSql('INSERT INTO consumptionrecord (id, peak, offpeak, xnight,date) values (?, ?, ?, ?, ?)', [customer.historicalConsumptions[i].id, customer.historicalConsumptions[i].peak, customer.historicalConsumptions[i].offpeak, customer.historicalConsumptions[i].xnight, customer.historicalConsumptions[i].date]);	           
        	}
            
	      }, function (err) {

              console.log(err.message);

             }  );
	 }
	 else{
		 console.log('failed to open db');
	 }
	}catch(e){
		console.log(e.message);
	}
	console.log('done tables');
}

function loadCustomer(){
	var userid = window.localStorage.getItem('userid');
	console.log('loading:'+userid);
	try{
		if(db == null){
			db = window.openDatabase("DBName", "1.0", "description", 5*1024*1024);
		}
		 if (db) {
			 db.transaction(
				        function(tx) {
				            tx.executeSql("SELECT * FROM customer where userid = ?", [userid],
				                function(tx, result) {
				            		customer = result.rows.item(0);
				            		initOverviewPageHelper();
				            	}
				            );
				            tx.executeSql("SELECT consumptionrecord.* FROM consumptionrecord  inner join expectedConsumptions on consumptionrecord.id = expectedConsumptions.cons_id inner join customer on expectedConsumptions.cust_id = customer.id where customer.userid = ? order by consumptionrecord.date desc", [userid],
					                function(tx, result) {
				            			customer.expectedConsumptions = new Array();
				            			console.log('load expected cons:' + result.rows.length);
					            		for(var i=0; i< result.rows.length;i++){
					            			customer.expectedConsumptions[i]=result.rows.item(i);
					            		}
					            	}
					        );
				            tx.executeSql("SELECT consumptionrecord.* FROM consumptionrecord  inner join historicalConsumptions on consumptionrecord.id = historicalConsumptions.cons_id inner join customer on historicalConsumptions.cust_id = customer.id where customer.userid = ? order by consumptionrecord.date desc", [userid],
					                function(tx, result) {
				            			customer.historicalConsumptions = new Array();
				            			console.log('load historical cons:' + result.rows.length);
					            		for(var i=0; i< result.rows.length;i++){
					            			customer.historicalConsumptions[i]=result.rows.item(i);
					            		}
					            	}
					        );
				            
			  }
				        , function (err) {
				        	 console.log("error while loading:" + err.message);
				        	}	        
			 );			
		 }
		 else{
			 console.log('failed to open db');
		 }
		}catch(e){
			console.log(e.message);
		}
}

function initOverview(){
	login('jan','test');
	alert(customer.name);
}

function initHistoryDetailPage(){
	var cons = findConsumption(detailId);
	getElement('recordDateRO').innerHTML =  convertStringToDate(cons.date)
	getElement('peakRO').innerHTML = getNumber(cons.peak).toFixed(2);
	getElement('offpeakRO').innerHTML =getNumber(cons.offpeak).toFixed(2);
	getElement('xnightRO').innerHTML =getNumber(cons.xnight).toFixed(2);
}

function findConsumption(id){
	for(var i=0;i<customer.historicalConsumptions.length;i++){
		if(customer.historicalConsumptions[i].id == detailId){
			return customer.historicalConsumptions[i];
		}
	}
}

function deleteMeterRecord(){
	var answer = confirm('Bent u zeker?');
	if(answer){
		for(var i=0;i<customer.historicalConsumptions.length;i++){
			if(customer.historicalConsumptions[i].id == detailId){
				customer.historicalConsumptions.splice(i,1);
				transform('HistoryDetailPage','HistoryPage', true);
				break;
			}
		}
	}
	
}

function showHistoricConsumptions(){
	var historicConsumptionContent = getElement('content');
	var  content = '<ul>';
	for(var i=0;i<customer.historicalConsumptions.length;i++){
		var total = getNumber(customer.historicalConsumptions[i].peak) + getNumber(customer.historicalConsumptions[i].offpeak) + getNumber(customer.historicalConsumptions[i].xnight);
		content = content + '<li><a class="noeffect" onclick="detailId=' + customer.historicalConsumptions[i].id + ';transform(\'HistoryPage\',\'HistoryDetailPage\');"><span class="number">';
		content = content + convertStringToDate(customer.historicalConsumptions[i].date) + '</span><span class="name">Electriciteit</span><span class="time">(';
		content = content + total.toFixed(2) +'kWh)</span><span class="arrow"></span></a></li>';
	}
	content = content + '</ul>';
	historicConsumptionContent.innerHTML = content;
}



function addMeterRecord(){
	console.log('add meterRecord');
	var length = customer.historicalConsumptions.length;
	customer.historicalConsumptions[length] = new Object();
	customer.historicalConsumptions[length].id = getId(customer.historicalConsumptions);
	customer.historicalConsumptions[length].peak=getElement('peakInput').innerHTML.replace(',','.');
	customer.historicalConsumptions[length].offpeak=getElement('offpeakInput').innerHTML.replace(',','.');
	customer.historicalConsumptions[length].xnight=getElement('xnightInput').innerHTML.replace(',','.');
	customer.historicalConsumptions[length].date=convertToDate(getElement('recordDateInput').innerHTML);
	if(!online){
		window.localStorage.setItem('refresh','true');
		console.log('refresh is true');
	}
	saveCustomer();
	loadCustomer();
}

function login(username, password){
	if(!online()){
		alert("Je moet online zijn om aan te loggen.\r\nControleer je verbinding.");
		return;
	}
	var xmlhttp=new XMLHttpRequest();
	xmlhttp.open("GET","customer?dispatch=loginAjax&username=" + username + "&password=" + password,true);
	xmlhttp.send();
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
			try {
				initCustomer(xmlhttp.responseText);
				getElement('errorLogin').innerHTML = "";
				getElement('errorLogin').style.visibility= 'hidden';
				transform('LogonPage', 'OverviewPage',false);
			} catch (e) {
				alert(e);
				alert(xmlhttp.status);
				getElement('errorLogin').innerHTML=xmlhttp.responseText + '<br><br>';
				getElement('errorLogin').style.visibility = 'visible';
				getElement('errorLogin').style.color = 'red';
			}
	    }
	  }
	
}

function refresh(){
	window.localStorage.removeItem('refresh');
}

function logout(){
	window.localStorage.clear();
	customer = null;
	transform('OverviewPage', 'LogonPage',true);	
}

function initOverviewPage(){
	if(customer == null){
		loadCustomer();
	}else{
		initOverviewPageHelper();
	}
		
}

function initOverviewPageHelper(){
	if(isIphone() || isBlackberry() || isAndroid()){
		getElement('iphoneOnly').style.visibility = 'visible';
		getElement('chart').width=250;
		getElement('chart').height=300;
		setImage('generatedImages/' + customer.userid +'_overview_iphone.png', 'chart');
	}else{
		getElement('chart').width=500;
		getElement('chart').height=500;
		getElement('iphoneOnly').style.visibility = 'hidden';
		setImage('generatedImages/' + customer.userid +'_overview_ipad.png', 'chart');
	}	
}


function setImage ( imageURL, field ) {
	getElement(field).src = imageURL;
}

function initHistoryPage(){
	showHistoricConsumptions();
}

function initInputPage(){
	var date = new Date();
	var day = date.getDate();
	var month = date.getMonth()+1;
	var year = date.getFullYear();
	document.getElementById('recordDateInput').innerHTML=day + '/'+month + '/' + year;
}
