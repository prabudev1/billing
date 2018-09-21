

$( document ).ready(function() {
	
	var CONST_RUPEE_TEXT = "Rs. ";
	
	$(".spanReportDays").html(APP_PROPS_JSON.REPORT_DAYS);
	$.ajax({
		type: 'get',
		url: '/billing/getReportData',
		contentType: "application/json; charset=utf-8",
        dataType   : "json",
		async : false,
		beforeSend: function() {
			$("#pageLoadingContainer").show();
		},
		success: function(data) {
			response = data;
			$("#revenue").html(roundOf2Digit(data.totRevenue));
			$("#custCount").html(roundOf2Digit(data.totCustomer));
			$("#invoiceCount").html(roundOf2Digit(data.totInvoices));
			$("#productCount").html(roundOf2Digit(data.totProducts));
			
			var dateMap = [];
				
            for (i = 0; i < data.billingItems.length; i++) {
                billRec = data.billingItems[i];
                var totalValue = roundOf2Digit(billRec.total);
                var dateValue = moment(billRec.createdOn)
                var dateStr = dateValue.format("DD-MMM-YYYY HH:mm:ss");
            	if (dateStr == "Invalid date" || dateStr == undefined) {
            		dateStr = "";
            	} else {
            		var dateString = dateValue.format("DD'MMM");
            		var existingvalue = getMapProperty(dateMap, dateString, totalValue);
            		var newvalue = 0;
            		if (existingvalue != null && existingvalue != undefined && existingvalue != "") {
            			newvalue = (+existingvalue) + (+totalValue);
            		} else {
            			newvalue = totalValue;
            		}
            		setMapProperty(dateMap, dateString, newvalue);
            	}
            	var custName = "NA";
            	if (billRec.customer != null) {
            		custName = billRec.customer.name;
            	}
                $("#billingRecords").append($("<tr>")
                		.append($("<td>").text(billRec.id))
                		.append($("<td>").text(custName))
                		.append($("<td>").text(roundOf2Digit(billRec.totItems)))
                		.append($("<td>").text(CONST_RUPEE_TEXT + roundOf2Digit(billRec.totValue)))
                		.append($("<td>").text(CONST_RUPEE_TEXT + roundOf2Digit((+billRec.gstStateVal) + (+billRec.gstCentralVal))))
                		.append($("<td>").text(CONST_RUPEE_TEXT + totalValue))
                		.append($("<td>").text(dateStr)));
            }
            
            var labelArray = Object.keys(dateMap).reverse();
            var dataArray = Object.values(dateMap).reverse();
            
            var lineChart = new Chart(document.getElementById("line-chart"), {
            	type: 'line',
	    		data: {
	    			labels: labelArray,
	    		    datasets: [{ 
	    		    		data: dataArray,
	    		        	label: "Invoice",
	    		        	borderColor: "#3cba9f",
	    		        	fill: false
	    		    	}]
	    		},
	    		options: {
	    			title: {
	    		  		display: true,
	    		  		text: 'Last ' + APP_PROPS_JSON.REPORT_DAYS + ' days of trending'
	    		  	}
	    		}
	    	});
            
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("OOPS, Something went wrong! Please try again or contact administrator");
		},
		complete: function() {
			$("#pageLoadingContainer").hide();
		}
	});
	
	function getMapProperty(map, key) {
		return map[key];
	}
	
	function setMapProperty(map, key, value) {
		if (value == null || value == undefined || value == "") {
			value = 0;
		}
		map[key] = value;
	}
	function roundOf2Digit(pValue) {
		if (pValue != "" && pValue != undefined && pValue != null) {
			var retunValue = parseFloat(pValue).toFixed(2);
			return retunValue.split(".00").join("");
		} else {
			return "0";
		}
	}
	
	function nullToString(pValue, defaultValue) {
		var retValue = pValue;
		if (pValue == "" || pValue == undefined || pValue == null || pValue == "null") {
			retValue = defaultValue;
		}
		return retValue;
	}
	

});