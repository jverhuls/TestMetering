var fromPage;
var toPage;
var currentTarget;


var callback = function animationEnd(event) {
    fromPage.removeEventListener('webkitTransitionEnd', callback);
    fromPage.removeEventListener('webkitAnimationEnd', callback);
    fromPage.className='page';
    toPage.className='current';
}

function blurAll(){
	var f = document.getElementsByTagName('input');
	for(var i=0;i<f.length;i++){
		f[i].blur();
	}
}

function getNumber(field){
	if(typeof field == 'number'){
		return field;
	}else{
		return parseFloat(field.replace(',','.'));
	}
}

function transform(from, to, reverse){
	 blurAll();
	 try{
		 cache.update();
	 }catch(e){}
	 window.scrollTo(0,0.9);
	 try{
		console.log('init' + to + '()');
	 	eval('init' + to + '()');
	 }catch(e){
	 }
	fromPage= document.getElementById(from);
	toPage = document.getElementById(to);
	if(reverse){
		fromPage.className='slideout current reverse';	
		toPage.className='current slidein reverse';
	}else{
		fromPage.className='slideout current';	
		toPage.className=' current slidein';
	}
	setTimeout(function(){
		if(reverse){
			fromPage.className='slideout current start reverse';
			toPage.className=' current slidein start reverse';
		}else{
			fromPage.className='slideout current start';
			toPage.className=' current slidein start';
		}
	}, 100);	
	fromPage.addEventListener('webkitTransitionEnd', callback);
	fromPage.addEventListener('webkitAnimationEnd', callback);
	
}

function isIpad(){
	return navigator.userAgent.indexOf('iPad') != -1;
}

function isIphone(){
	return navigator.userAgent.indexOf('iPhone') != -1;
}

function isBlackberry(){
	return navigator.userAgent.indexOf('BlackBerry') != -1;
}

function isAndroid(){
	return navigator.userAgent.indexOf('Android') != -1;
}



function online(){
	return (navigator.onLine) ? true : false;
}

function switchPage(){
	
}

function getElement(id){
	return document.getElementById(id);
}

function getValue(id){
	return document.getElementById(id).value;
}

function convertToDate(date){
	var dateArray = date.split("/");
	console.log('date:' + dateArray[2] + '-' +dateArray[1] + '-' + dateArray[0]);
	return appendZero(dateArray[2]) + '-' +appendZero(dateArray[1]) + '-' + dateArray[0]; 
}

function convertStringToDate(string){
	var dateArray = string.split("-");
	console.log('date:' + dateArray[2] + '/' +dateArray[1] + '/' + dateArray[0]);
	
	return appendZero(dateArray[2]) + '/' +appendZero(dateArray[1]) + '/' + dateArray[0];
}

function appendZero(num){
	if(parseInt(num) < 10 && num[0] != 0){
		return '0'+num;
	}else{
		return num;
	}
	
}

function getId(list){
	var id = -1;
	for(var i=0;i<list.length;i++){
		if(parseInt(list[i].id) <= id){
			id = parseInt(list[i].id) - 1;
		}
	}
	return id;
}


function inputDate(target){
	currentTarget = target;
	date = target.innerHTML.split('/');
	
	var now = new Date();
	var days = { };
	var years = { };
	var months = { 1: 'Jan', 2: 'Feb', 3: 'Mar', 4: 'Apr', 5: 'Mei', 6: 'Jun', 7: 'Jul', 8: 'Aug', 9: 'Sep', 10: 'Okt', 11: 'Nov', 12: 'Dec' };
	
	for( var i = 1; i < 32; i += 1 ) {
		days[i] = i;
	}

	for( i = now.getFullYear()-2; i < now.getFullYear()+1; i += 1 ) {
		years[i] = i;
	}

	SpinningWheel.addSlot(days, 'right', date[0]);
	SpinningWheel.addSlot(months, '', date[1]);
	SpinningWheel.addSlot(years, 'right', date[2]);
	
	SpinningWheel.setCancelAction(cancel);
	SpinningWheel.setDoneAction(done);
	
	SpinningWheel.open();

}

function inputMeter(target){
	currentTarget = target;
	meter = target.innerHTML.split("");
	var numbers = { 0: 0, 1: 1, 2: 2, 3: 3, 4: 4, 5: 5, 6: 6, 7: 7, 8: 8, 9: 9 };
	SpinningWheel.addSlot(numbers, 'right',meter[0]);
	SpinningWheel.addSlot(numbers, 'right',meter[1]);
	SpinningWheel.addSlot(numbers, 'right',meter[2]);
	SpinningWheel.addSlot(numbers, 'right',meter[3]);
	SpinningWheel.addSlot(numbers, 'right',meter[4]);
	SpinningWheel.addSlot(numbers, 'right',meter[5]);
	SpinningWheel.addSlot({ separator: ',' }, 'readonly shrink');
	SpinningWheel.addSlot(numbers, 'right',meter[7]);
	SpinningWheel.addSlot(numbers, 'right',meter[8]);
	
	SpinningWheel.setCancelAction(cancel);
	SpinningWheel.setDoneAction(doneMeter);
	
	SpinningWheel.open();
}


function cancel(){
	
}

function done(){
	var results = SpinningWheel.getSelectedValues();
	currentTarget.innerHTML = results.keys[0]+'/'+results.keys[1]+'/' + results.keys[2];
}

function doneMeter(){
	var results = SpinningWheel.getSelectedValues();
	currentTarget.innerHTML = results.keys[0]+results.keys[1]+results.keys[2]+results.keys[3]+results.keys[4]+results.keys[5]+','+results.keys[7]+results.keys[8];
}
