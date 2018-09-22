var AMOUT_IN_WORDS_1 = ['','one ','two ','three ','four ', 'five ','six ','seven ','eight ','nine ','ten ','eleven ','twelve ','thirteen ','fourteen ','fifteen ','sixteen ','seventeen ','eighteen ','nineteen '];
var AMOUT_IN_WORDS_2 = ['', '', 'twenty','thirty','forty','fifty', 'sixty','seventy','eighty','ninety'];

$( document ).ready(function() {

	$("#spanCGSTPercent").html(APP_PROPS_JSON.GST_CENTRAL_PERCENT);
	$("#spanSGSTPercent").html(APP_PROPS_JSON.GST_STATE_PERCENT);

	function GetParameterValues(param) {  
        var url = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');  
        for (var i = 0; i < url.length; i++) {  
            var urlparam = url[i].split('=');  
            if (urlparam[0] == param) {  
                return urlparam[1];  
            }  
        }  
    }
	
	var billId = GetParameterValues("bid");
	$.ajax({
		type: 'get',
		url: '/billing/get/' + billId,
		contentType: "application/json; charset=utf-8",
        dataType   : "json",
		async : true,
		beforeSend: function() {
			$("#pageLoadingContainer").show();
		},
		success: function(data) {
			$("#billNumber").html(paddingBillingNo(data.id));
			
			var dateValue = moment(data.createdOn)
			//var dateStr = dateValue.format("DD-MMM-YYYY HH:mm:ss");
			var dateStr = dateValue.format("DD-MMM-YYYY");
         	if (dateStr == "Invalid date" || dateStr == undefined) {
         		dateStr = "";
         	}
         	$("#billGenerated").html(dateStr);
         	$("#subTotal").html(roundOf2Digit(data.totValue));
         	$("#cgstVal").html(roundOf2Digit(data.gstCentralVal));
         	$("#sgstVal").html(roundOf2Digit(data.gstStateVal));
         	
         	var grandTotal = roundOf2Digit(data.total)
         	$("#grandTotal").html(grandTotal);
         	
         	var amountStr = convertAmountToWords(grandTotal);
        	$("#amount-in-words").html("Rs. " + amountStr);
        	$("#amount-in-words").css("text-transform", "capitalize");
         	
        	setCustInfo(data.customer);
        	
            for (i = 0; i < data.billingItems.length; i++) {
                billRec = data.billingItems[i];
                var prdName = "NA";
            	var prdValue = "NA";
            	if (billRec.product != null) {
            		prdName = billRec.product.name;
            		prdValue = billRec.product.value;
            	}
                $("#billingRecords").append($("<tr>")
                		.append($("<td>").text( ((+i) + 1)))
                		.append($("<td>").text(prdName))
                		.append($("<td>").text(prdValue))
                		.append($("<td>").text(billRec.qty))
                		.append($("<td>").text(roundOf2Digit(billRec.value))));
            }
            window.print();
            window.close();
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert("OOPS, Something went wrong! Please try again or contact administrator");
		},
		complete: function() {
			$("#pageLoadingContainer").hide();
		}
	});
	
	function paddingBillingNo(paraBillNo) {
		var strBillNo = "" + paraBillNo;
		var paddLength = ((+APP_PROPS_JSON.BILL_NO_LEADING_ZERO_LENGTH) - strBillNo.length);
		
		for (cntI=0; cntI < paddLength; cntI++){
			strBillNo = "0" + strBillNo;
		}
	    return strBillNo;
	}
	function roundOf2Digit(pValue) {
		if (pValue != "" && pValue != undefined && pValue != null) {
			var retunValue = parseFloat(pValue).toFixed(2);
			return retunValue.split(".00").join("");
		} else {
			return "";
		}
	}
	
	function nullToString(pValue, defaultValue) {
		var retValue = pValue;
		if (pValue == "" || pValue == undefined || pValue == null || pValue == "null") {
			retValue = defaultValue;
		}
		return retValue;
	}
	
	
	function inWords (num,) {
	    if ((num = num.toString()).length > 9) return 'overflow';
	    n = ('000000000' + num).substr(-9).match(/^(\d{2})(\d{2})(\d{2})(\d{1})(\d{2})$/);
	    if (!n) return; var str = '';
	    str += (n[1] != 0) ? (AMOUT_IN_WORDS_1[Number(n[1])] || AMOUT_IN_WORDS_2[n[1][0]] + ' ' + AMOUT_IN_WORDS_1[n[1][1]]) + 'crore ' : '';
	    str += (n[2] != 0) ? (AMOUT_IN_WORDS_1[Number(n[2])] || AMOUT_IN_WORDS_2[n[2][0]] + ' ' + AMOUT_IN_WORDS_1[n[2][1]]) + 'lakh ' : '';
	    str += (n[3] != 0) ? (AMOUT_IN_WORDS_1[Number(n[3])] || AMOUT_IN_WORDS_2[n[3][0]] + ' ' + AMOUT_IN_WORDS_1[n[3][1]]) + 'thousand ' : '';
	    str += (n[4] != 0) ? (AMOUT_IN_WORDS_1[Number(n[4])] || AMOUT_IN_WORDS_2[n[4][0]] + ' ' + AMOUT_IN_WORDS_1[n[4][1]]) + 'hundred ' : '';
	    str += (n[5] != 0) ? ((str != '') ? 'and ' : '') + (AMOUT_IN_WORDS_1[Number(n[5])] || AMOUT_IN_WORDS_2[n[5][0]] + ' ' + AMOUT_IN_WORDS_1[n[5][1]]) + '' : '';
	    return str;
	}

	function convertAmountToWords(num) {
		var numArr = (num + "").split(".");
		var amountStr = inWords(numArr[0]);
		amountStr = $.trim(amountStr);
		if (numArr.length > 1) {
			amountStr += ", " +inWords(numArr[1]) + " paisa";
		}
		return amountStr;
	}
	
	function appendAddress(fullLine, addLine, cancatenateChar) {
		if (addLine != "" && addLine != null) {
			fullLine += cancatenateChar + addLine; 
		}
		return fullLine;
	}
	function setCustInfo(custItem) {
		if (custItem != null) {
			var custInfo = ""
			var nameInfo = custItem.name + " (" + custItem.mobile + ")";
			
			var addressText = "";
			addressText = appendAddress(addressText, custItem.addressLine1, ", ");
			addressText = appendAddress(addressText, custItem.addressLine2, ", ");
			addressText = appendAddress(addressText, custItem.city, ", ");
			addressText = appendAddress(addressText, custItem.state, ", ");
			addressText = appendAddress(addressText, custItem.pinCode, " - ");
			if (addressText.startsWith(", ")) {
				addressText = addressText.substring(2, addressText.length);
			}
			if (addressText.startsWith(" - ")) {
				addressText = addressText.substring(3, addressText.length);
			}
			custInfo = nameInfo;
			if (addressText != "") {
				custInfo += "<br>" + addressText;
			} 
			$("#custInfo").html(custInfo);
		}
	}
});