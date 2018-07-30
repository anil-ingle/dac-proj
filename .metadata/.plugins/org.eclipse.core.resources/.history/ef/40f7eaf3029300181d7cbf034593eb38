var jq=$;
	
	var script = document.createElement('script');
	script.src = 'jquery.min.js';
	script.type = 'text/javascript';
	document.getElementsByTagName('head')[0].appendChild(script);
	///home/anil/Desktop/developer/OnlineParkingProject/src/main/webapp/user/node_modules/toastr/build
	var sc = document.createElement('script');
	sc.src = 'node_modules/toastr/build/toastr.min.js';
	sc.type = 'text/javascript';
	document.getElementsByTagName('head')[0].appendChild(sc);
	
	var st = document.createElement('style');
	st.src = 'node_modules/toastr/build/toastr.min.css';
	st.type = 'stylesheet';
	document.getElementsByTagName('head')[0].appendChild(st);
	
	
					jq(document).ready(function() {
						jq.get("../user?cid="+ "cityLink", function(data) {
							var a = "<option value=' '>Select city</option>";
							jq.each(JSON.parse(data), function(k, v) {
								a += "<option value='"+v.cityId+"'>"
										+ v.cityName + "</option>";
							});
							jq("#city").html(a);

						});
					});
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
					
					
			function formSubmit() {
				let error=false;
				let form={};
				let fName=document.getElementById("fname").value;
				let lName=document.getElementById("lname").value;
				let email=document.getElementById("reg-email").value;
				let pass=document.getElementById("reg-pass").value;
				let mobileNumber=document.getElementById("reg-mob").value;
				let dob=document.getElementById("dob").value;
				let male=document.getElementById("male").checked;
				let gender='';
				let fmale=document.getElementById("fmale").checked;
				let cityId=document.getElementById("city").value;
				
				if(male==true && fmale==false || fmale==true && male==false){
					if(male){
						gender="male";
						form.gender=gender;
					}else{
						gender="female";
						form.gender=gender;
					}
					
				}else{
					error=true;
					
				}
				if(!fName=='' || fName.length>0){
					form.fName=fName;
					
				}else{
					error=true;
					
				}	
				if(!lName=='' || lName.length>0){
					form.lName=lName;
					
				}else{
					error=true;
					
				}	
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
				if(!mobileNumber=='' || mobileNumber.length>0){
					form.mobileNumber=mobileNumber;
					
				}else{
					error=true;
					
				}
				if(!dob=='' || dob.length>0){
					form.dob=dob;
					
				}else{
					error=true;
					
				}	
				if(!cityId=='' || cityId.length>0){
					form.cityId=cityId;
					
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
			            url:  "../user?link="+ 'reg',
			            type: "POST",
			            dataType: "json",
			            contentType: 'application/json; charset=utf-8',
			            data: JSON.stringify(form),
			            success: function (response) {
			                if (response) {
			                	
			                	toastr.success("Registration successfully.");
			                	resetReg();
			                	 
			                }else{
			                	toastr.error("Record not insertrd Please contact support team.");
			                	resetReg();
			                }
			            },
			            error: function (xhr, status, err) {
			            	toastr.error("Record not insertrd Please contact support team.");
			            	resetReg();
			            	  console.log("fail...")
			            	  
			            }
			        });
					
				}
					 
		}	
			
			
			function resetReg(){
			
	              jq('input[type="text"],textarea').val('');

			}