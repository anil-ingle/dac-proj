	var jq=$;
	var ajaxPrefix="http://localhost:2020/onlineparking/";
	jq(document).ready(function() {
		 let _user = JSON.parse(sessionStorage.getItem('user'));
		 if(_user){
			// do something
		 }else{
			 window.location.href = ajaxPrefix;
		 }
		
		});