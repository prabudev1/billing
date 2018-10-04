

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
			var productDateMap = [];
			var prodItem ;
            for (i = 0; i < data.billingItems.length; i++) {
                billRec = data.billingItems[i];
                var totalValue = roundOf2Digit(billRec.total);
                var dateValue = moment(billRec.billingDate)
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
            		
            		/* Iterate product to find the count */
            		for (cntPrd = 0; cntPrd < billRec.billingItems.length; cntPrd++) {
            			prodItem = billRec.billingItems[cntPrd];
            			var lProductName = prodItem.product.code;
            			var lValue = prodItem.value;
            			
            			var prdExistingvalue = getMapProperty(productDateMap, lProductName, lValue);
                		var prdNewValue = 0;
                		if (prdExistingvalue != null && prdExistingvalue != undefined && prdExistingvalue != "") {
                			prdNewValue = (+prdExistingvalue) + (+lValue);
                		} else {
                			prdNewValue = lValue;
                		}
                		setMapProperty(productDateMap, lProductName, prdNewValue);
            		}
            		
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
            
            var prdLabelArray = Object.keys(productDateMap).reverse();
            var prdDataArray = Object.values(productDateMap).reverse();
            
            var myDoughnutChart = new Chart(document.getElementById("doughnut-chart"), {
                type: 'doughnut',
                data: {
                	    datasets: [{ 
                	    	data: prdDataArray,
                    	    backgroundColor: [
                    	    	'rgb(255, 99, 132)',
                    	    	'rgb(255, 159, 64)',
                    	    	'rgb(255, 205, 86)',
                    	    	'rgb(75, 192, 192)',
                    	    	'rgb(54, 162, 235)',
                    	    	'rgb(153, 102, 255)',
                    	    	'rgb(201, 203, 207)',
                                '#e53935',
                                '#8e24aa',
                                '#d81b60',
                                '#1e88e5',
                                '#5e35b1',
                                '#00897b',
                                '#3949ab',
                                '#039be5',
                                '#c0ca33',
                                '#fb8c00',
                                '#43a047',
                                '#ff5722',
                                '#424242',
                                '#ffb300',
                                '#6d4c41',
                                '#546e7a'
                            ]
                	    }],
                	    labels: prdLabelArray
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