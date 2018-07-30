	
	var jq=$;
	
	/*var script = document.createElement('script');
	script.src = 'jquery.min.js';
	script.type = 'text/javascript';
	document.getElementsByTagName('head')[0].appendChild(script);*/
	let aid='';
	
					jq(document).ready(function() {
						jq.get("../user?cid="+ "cityLink", function(data) {
							var a = "<option value=' '>Select city</option>";
							jq.each(JSON.parse(data), function(k, v) {
								a += "<option value='"+v.cityId+"'>"
										+ v.cityName + "</option>";
							});
							jq("#city").html(a);

						});
						jq("#city").change(function() {
						var cityId = $(this).val();
						jq.get("../user?cityId="+ cityId+"&link2="+"cityAreaLink",function(data) {
						var a = "<option value=' '>Select area</option>";
						jq.each(JSON.parse(data),function(k,v) {
						a += "<option value='"+v.areaId+"'>"+ v.areaName+ "</option>";
					});
						jq("#area").html(a);
					});
		});
		
	
						jq("#area").change(function() {
						 aid = jq(this).val();
						jq.get("../user?areaId="+ aid+"&link2="+"areaSlot",function(data) {
					var data=JSON.parse(data);
						getSlot(data);
						});
		             })
		       });
					

	var slot='';
	function getSlot(data) {
	
	if(data=="park"){
	document.getElementById("demo").innerHTML = displySlot(slot);
	}else{
		slot=data;	
	}
	}
	let slotIds='';
	function displySlot(data) {
		let val=data.length;
		slotIds=''
		let c='';
		let minVal=0;
		if(val>=10)
			minVal=val/2;
		for(let count=0;count<val;count++){
			if(data[count].isReserved==1){
				c=c+"<button   onclick='myFunction1(this)' value="+data[count].slotNumber+" id="+data[count].slotNumber+"   class='search-button-reserved'>slot"+ data[count].slotNumber+"</button>";

			}else{
			c=c+"<button  onclick='myFunction1(this)'value="+data[count].slotNumber+" id="+data[count].slotNumber+"   class='search-button'>slot"+ data[count].slotNumber+"</button>";
			}
			if(minVal!=0){
			if(count==minVal)
				c=c+"</br>";
			}
		}
		
	    return c;
	}
	
	function myFunction1() {
		
		
		if(slotIds==''){
			if(event.currentTarget.className=="search-button")
			slotIds=event.target.id;
		}else{
			if(event.currentTarget.className=="search-button")
			slotIds=slotIds+","+event.target.id;
		}
		
		
	   var property = document.getElementById(event.target.id);
        property.style.backgroundColor = "red";

		
	}
	
	let timeRequired='';
	function timeFunction() {
		timeRequired=event.target.value;
		
		
		
	}
	function onReset(){
		//select-area  select-city
		timeRequired='';
		slotIds='';
		/*jq('#select-area').prop('selectedIndex',0);
		jq('#select-city').prop('selectedIndex',0);
		jq('#select-time').prop('selectedIndex',0);*/
		
		
		
	}
	
	function onRefres(){
		timeRequired='';
		slotIds='';
		/*jq('#city').prop('selectedIndex',0);
		jq('#area').prop('selectedIndex',0);
		jq('#select-time').prop('selectedIndex',0);*/
		getSlot('park');
		
		
	}
		
		
	function bookNow() {
		let errorMsg=false;
		let billAmount=0;
		  var record={};
		  if(slotIds==''){
			  errorMsg=true;
		  }else{
			  record.slotIds=slotIds;
			  }
			if(timeRequired==''){
				errorMsg=true;
			}else{
				 record.timeRequired=timeRequired;
			}
		if(errorMsg){
			toastr.warning("Please Select all fields");
		}else{
			let ids=slotIds.split(",");
			
		for(let i=0;i<ids.length;i++){
			if(ids[i]<5){
				billAmount=billAmount+15*timeRequired;
			}else{
				billAmount=billAmount+30*timeRequired;
			}
			
		}
		woletRendor(billAmount);
		jq("#getCode").html(true);
		jq("#getCodeModal").modal("toggle");
		 
		 
		}
		
		
		/*
		var form={};
		form.ids=slotIds;
		form.time=timeRequired;
		 jq("#getCode").html(true);
          $("#getCodeModal").modal('show');
          jq("#getCodeModal").modal("toggle");
		
		jq.ajax({
            url:  "../user?link="+ 'plink',
            type: "POST",
            dataType: "json",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(form),
            success: function (response) {
                if (response) {
                	woletRendor();
                   console.log("success..."+response);
                   jq("#getCode").html(response);
                   $("#getCodeModal").modal('show');
                   jq("#getCodeModal").modal("toggle");
                   $("#getCode").html(msg);
                }
            },
            error: function (xhr, status, err) {
            	jq('#getCodeModal').modal('hide')
            	  console.log("fail...")
            }
        });*/
		
		
		
		}
		
		
		function woletRendor(billAmount){
			let _user = JSON.parse(sessionStorage.getItem('user'));
			let nameLine="<div class='wollet-name'><div>Name:</div><div id='wname' class='wollet-name-input'>"+_user.fName+"  "+_user.lName+"</div></div>";
			let amount="<div class='wollet-amount'><div>Amount:</div><div id='wtotal' class='wollet-amount-input'>"+_user.totalAmount+"</div></div>";
			let bill="<div class='wollet-bill'><div>Bill:</div><div id='wbill' class='wollet-bill-input'>"+billAmount+"</div></div>";
			let bt="<div class='btn-wolet'><div class='bt-continue'><button class='btc-continue' onclick='Continue()'>Continue</button></div></div>";
         	
			 document.getElementById("modal-info").innerHTML = nameLine+amount+bill+bt;
			
			
		}
		
		function Continue(){
			let _user = JSON.parse(sessionStorage.getItem('user'));
			let bookedSlots=slotIds;
			let timeTaken=timeRequired;
			let wName=document.getElementById("wname");
			let cName=wName.innerText;
			let wTotal=document.getElementById("wtotal");
			let cTotal=wTotal.innerText;
			let wBill=document.getElementById("wbill");
			let cBill=wBill.innerText;
			if(cName=='' || cTotal=='' ||cBill=='' || bookedSlots=='' || timeTaken=='' || aid==''){
				//alert("Wollet  error..");
				toastr.warning("Wollet  error..");
			}else{
				let wolletData={};
				
				wolletData.wTotal=cTotal;
				wolletData.wBill=cBill;
				wolletData.bookedSlots=bookedSlots;
				wolletData.timeTaken=timeTaken;
				wolletData.userId=_user.id;
				wolletData.areaId=aid;
				
				jq.ajax({
		            url:  "../user?link="+ 'wolletlink',
		            type: "POST",
		            dataType: "json",
		            contentType: 'application/json; charset=utf-8',
		            data: JSON.stringify(wolletData),
		            success: function (response) {
		                if (response) {
		                	jq('#getCodeModal').modal('hide');
		                	toastr.success("Booking successfully.");
		                  this.reset();
		                }
		            },
		            error: function (xhr, status, err) {
		            	jq('#getCodeModal').modal('hide');
		            	toastr.error("Booking not successfully. Please contact support team.");
		            	this.reset();
		            	 
		            }
		        });
				
			}

		}
	
        