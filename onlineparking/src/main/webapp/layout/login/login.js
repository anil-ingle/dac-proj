var jq=$;
var ajaxPrefix="http://localhost:2020/onlineparking/";
	
	
					
			function userLogin() {
				let error=false;
				let form={};
				
				let email=document.getElementById("email").value;
				let pass=document.getElementById("pass").value;
					
				if(!email=='' || email.length>0){
					form.email=email;
					
				}else{
					error=true;
					
				}	
				if(!pass=='' || pass.length>0){
					form.pass=pass;
					
				}else{
					error=true;
					
				}	
				
				
				
				if(error){
					toastr.error("Please all fiels are mandetory.");
					
				}else{
					let currentTime = new Date().toJSON().slice(0,10).replace(/-/g,'/');
					form.currentTime=currentTime;
					console.log(currentTime)
					
					jq.ajax({
			            url:  ajaxPrefix+ "mvc/login?un="+ email+"&password="+pass,
			            type: "GET",
			            dataType: "json",
			            contentType: 'application/json; charset=utf-8',
			         
			            success: function (response) {
			                if (response) {
			                	console.log(response)
			                	
			                	 sessionStorage.setItem('user', JSON.stringify(response));
			                	if(response.roll==1)
			                	window.location.href = ajaxPrefix+"layout/user/user.html";
			                	toastr.success("login successfully.");
			                	resetReg();
			                	 
			                }else{
			                	toastr.error("Username or Password incorrect.");
			                	resetReg();
			                }
			            },
			            error: function (xhr, status, err) {
			            	toastr.error("Login not successfully. Please contact support team.");

			            	resetReg();
			            	  console.log("fail...")
			            	  
			            }
			        });
					
				}
					 
		}	
			
			
			function resetReg(){
			
	              jq('input[type="text"],textarea').val('');

			}