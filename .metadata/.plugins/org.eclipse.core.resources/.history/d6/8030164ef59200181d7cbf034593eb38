var jq=$;
	
	/*var script = document.createElement('script');
	script.src = 'jquery.min.js';
	script.type = 'text/javascript';
	document.getElementsByTagName('head')[0].appendChild(script);*/
	///home/anil/Desktop/developer/OnlineParkingProject/src/main/webapp/user/node_modules/toastr/build
	var sc = document.createElement('script');
	sc.src = 'node_modules/toastr/build/toastr.min.js';
	sc.type = 'text/javascript';
	document.getElementsByTagName('head')[0].appendChild(sc);
	
	var st = document.createElement('style');
	st.src = 'node_modules/toastr/build/toastr.min.css';
	st.type = 'stylesheet';
	document.getElementsByTagName('head')[0].appendChild(st);
	
	
					/*
					 * 
					 * console.log("fName  "+fName);
				console.log("lName "+lName);
				console.log("email "+email);
				console.log("pass "+pass);
				console.log("mobileNumber "+mobileNumber);
				console.log("dob "+dob);
				console.log("male "+male);
				console.log("fmale "+fmale);
				console.log("cityId "+cityId);
					 * 
					 */
					
					
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
			            url:  "../user?link="+ 'login',
			            type: "POST",
			            dataType: "json",
			            contentType: 'application/json; charset=utf-8',
			            data: JSON.stringify(form),
			            success: function (response) {
			                if (response) {
			                	console.log(response)
			                	
			                	 sessionStorage.setItem('user', JSON.stringify(response));
			                	if(response.roll==1)
			                	window.location.href = "http://localhost:2020/OnlineParkingProject/user/user-main.html";
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