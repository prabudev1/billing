
$(document).ready(function() {
	
	
	var CGST_PERCENT = APP_PROPS_JSON.GST_CENTRAL_PERCENT;
	var SGST_PERCENT = APP_PROPS_JSON.GST_STATE_PERCENT;
	var PUBLIC_BILL_DETAIL_LIST = [];
	
	// Every time a modal is shown, if it has an autofocus element, focus on it.
	$('.modal').on('shown.bs.modal', function() {
	  $(this).find('[autofocus]').focus();
	});
	
	$("#spanCGSTPercent").html(CGST_PERCENT);
	$("#spanSGSTPercent").html(SGST_PERCENT);
	
	$(".loader-container").hide();
	$(".txt-loader").hide();
	
	$("#btnSave").click(function(event) {
	    var form = $("#customerForm")
	    if (form[0].checkValidity()) {
	    	event.preventDefault();
	    	event.stopPropagation();
	    	var dataJson = { 
				name: $("#inputName").val(), 
				email : $("#inputEmail").val(), 
				mobile : $("#inputMobile").val(),
				aadhar : $("#inputAadhar").val(),
				gstin : $("#inputGstin").val(),
				type : $("#inputType").val(),
				addressLine1 : $("#inputAddress").val(),
				addressLine2 : $("#inputAddress2").val(),
				city : $("#inputCity").val(),
				state : $("#inputState").val(),
				country : "",
				pinCode : $("#inputZip").val(), 
				dob : $("#hiddenDob").val(),
				gender : $("#inputGender").val(),
				isActive : $('#isActive').is(":checked"),
				createdBy : 'ADMIN',
			};
	    	
	    	$.ajax({
	    		type: 'post',
	    		url: 'customer/add',
	    		contentType: "application/json; charset=utf-8",
	            dataType   : "json",
				data: JSON.stringify(dataJson),
				async : true,
				beforeSend: function() {
					$("#pageLoadingContainer").show();
				},
				success: function(data) {
					onSuccessfulCustomerAddition(dataJson, data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					if (jqXHR.status == "409" ) {
						alert("Customer mobile number already exist");
					} else {
						alert("OOPS, Something went wrong! Please try again or contact administrator");
					}
				},
				complete: function() {
					$("#pageLoadingContainer").hide();
				}
	    	});
	    }
	    form.addClass('was-validated');
	});
	
	function onSuccessfulCustomerAddition(pDataJson, pCustomerOrgId) {
		$("#inNameOrPhone").val(pDataJson.name + " (" + pDataJson.mobile +")");
		setCustomerDet(pDataJson, pCustomerOrgId);
		$("#customerModal").modal('hide');
		showAlert("#cust-success-alert");
	}
	
	var $input = $(".typeAheadCustName");
	$input.typeahead({
		source:  function (query, process) {
			return $.ajax({
				url: '/customer/getSearchList?q=' + query,
				dataType: "json",
				async : true,
				beforeSend: function() {
					$(".txt-loader").show();
				},
				success: function(data) {
					data.push({id: "-2", name: "Can't find? Add new customer!"});
					return process(data);
				},
				error: function(jqXHR, textStatus, errorThrown) {
					return process([{id: "-1", name: "Service unavailable, try again or contact administrator"}]);
				},
				complete: function() {
					$(".txt-loader").hide();
				}
			});
		},		
		matcher: function (item) { return true },
		displayText: function (item) {
			if (item.id > 0)
				return item.name + " (" + item.mobile +")";
			else
				return item.name;
		},
		minLength: 3,
		autoSelect: false
	});
	
	$input.change(function() {
	  var current = $input.typeahead("getActive");
	  $("#cust-address").html("");
	  $("#profile-img").hide();
	  if (current) {
		  if (current.id == "-1" ) { //Error
			  $input.val("");
			  return false;
		  } else if (current.id == "-2" ) { // Add new customer
			  $input.val("");
			  resetForm();
			  $("#customerModal").modal();
			  return false;
		  } else {
			  setCustomerDet(current, current.id);
//			  // Some item from your model is active!
//			  if (current.name == $input.val()) {
//				  // This means the exact match is found. Use toLowerCase() if you want case insensitive match.
//			  } else {
//				  // This means it is only a partial match, you can either add a new item
//				  // or take the active if you don't want new items
//			  }
		  }
	  } else {
		  //  Nothing is active so it is a new value (or maybe empty value)
	  }
	});
	
	$("#inNameOrPhone").focus();
	
	function trimAddress(str, isNeeded) {
		str = str.split(',,').join(",");
		str = $.trim(str);
		str = str.replace(/^-|-$/g,'');
		str = $.trim(str);
		str = str.replace(/^,|,$/g,'');
		str = $.trim(str);
		if (str != "" & isNeeded) {
			str += "<br>";
		}
		return str;
	}
	
	function setCustomerDet(custItem, pCustomerOrgId) {
		var addressText1 = custItem.name + " (" + custItem.mobile + ")";
		var addressText2 = custItem.addressLine1 + ", " + custItem.city;
		var addressText3 = custItem.state + " - " + custItem.pinCode;
		$("#hiddenCustomerId").val(custItem.id);
		$("#cust-address").html(trimAddress(addressText1, true) + trimAddress(addressText2, true) + trimAddress(addressText3, false));
		$("#profile-img").show();
		if (custItem.gender == "F") {
			$("#profile-img").attr("src" , "assets/img/user-female-icon.png")
		} else if (custItem.gender == "M") {
			$("#profile-img").attr("src" , "assets/img/user-male-icon.png")
		} else {
			$("#profile-img").attr("src" , "assets/img/hm-user.png")
		}
		$("#hiddenCustomerId").val(pCustomerOrgId);
		$("#inProductName1").focus();
	}
	function resetForm () {
		$("#inputName").val(""); 
		$("#inputEmail").val(""); 
		$("#inputMobile").val("");
		$("#hiddenDob").val("");
		$("#inputGender").val("");
		$("#inputAddress").val("");
		$("#inputAddress").val("");
		$("#inputAddress2").val("");
		$("#inputCity").val("");
		$("#inputState").val("");
		$("#inputZip").val("");		
		$('#isActive').attr('checked', true);
		$("#customerForm").removeClass('was-validated');
	}
	
	function resetProductForm () {
		$("#hiddenProductRowId").val("");
		$("#inputCode").val(""); 
		$("#formProductName").val(""); 
		$("#inputProductDesc").val("");
		$("#inputProductMeasurement").val("");
		$("#inputProductType").val("");
		$("#inputProductValue").val("");	
		$('#isProductActive').attr('checked', true);
		$("#productForm").removeClass('was-validated');
	}
	
	$("#inputAddress").typeahead({
		source:  ['123 Main Road'],
		autoSelect: false
	});
	
	$("#inputAddress2").typeahead({
		source:  ['Apartment, studio, or floor'],
		autoSelect: false
	});
	
	$("#inputCity").typeahead({
		source:  ['Chennai', 'Trichy', 'Madurai', 'Tirunelveli', 'Thoothukudi', 'Pudukkottai', 'Dindigul', 'Salem'],
		autoSelect: false
	});
	
	$("#inputZip").typeahead({
		source:  ['600071', '62008', '625010', '627007', '628001', '622001', '624001', '636001'],
		autoSelect: false
	});
	
	$('#inputDob').daterangepicker({
			singleDatePicker: true,
	    	showDropdowns: true,
	    	minYear: 1901,
	    	maxYear: parseInt(moment().format('YYYY'), 10),
	    	locale: {format: 'DD-MMM-YYYY'}
	 	}, function(start, end, label) {
	  		$("#hiddenDob").val(start.format('YYYY-MM-DD'));
	  	}
	);	
	
	$('#inputDob').val("");
	
	
	function getSingleBillRow(rowObj) {
		var rowText = "<tr id='billRowItem" + rowObj.sno + "'>";
		var noOfRecords = $("#billItems tr").length ;		
		rowText += "<td class='tbl-bill-sno'><span class='clsSno' id='spanSno" + rowObj.sno + "'>" + ((+noOfRecords) + 1) + "</span></td>";
		rowText += "<td class='tbl-bill-prd dropdown'>" +
						"<input type='text' class='clsProductName bill-form-control form-control' " +
							" id='inProductName" + rowObj.sno + "' " +
							" placeholder='Product Name' " +
							" value='" + rowObj.name + "' " +
							" data-provide='typeahead' " +
							" onClick='this.setSelectionRange(0, this.value.length)'>" +
					"<div class='invalid-feedback'>Missed product name.</div><div class='invalid-feedback invalid-product' id='invalid-product-" + rowObj.sno + "' style='display: none;'>Invalid product.</div></td>";
		rowText += "<td class='tbl-bill-qty'>" +
							"<input type='number' step='.01' class='clsQty bill-form-control form-control' " +
									" id='inQty" + rowObj.sno + "' placeholder='0' " +
									" value='" + rowObj.qty + "'>" +
					"<div class='invalid-feedback'>Missed Qty.</div></td>";
		rowText += "<td class='tbl-bill-price'> <span id='spanPrice" + rowObj.sno + "'>" + rowObj.price + "</span><input type='hidden' class='clsPrice' id='inPrice" + rowObj.sno + "' value='" + rowObj.price + "'></td>";
		rowText += "<td class='tbl-bill-action'><a tabindex='-1' href='javascript:void(0)' id='linkRemove" + rowObj.sno + "'><span class='fa fa-remove remove-icon'></span></a></td>";
		return rowText;
	}
	
	function getBillsRow(billRowArray) {
		var rowContent = "";
		for (i=0; i < billRowArray.length; i++) {
			rowContent += getSingleBillRow(billRowArray[i]);
		}
		return rowContent;
	}
	
	function setProductLoader(element) { 
		$(element).after("<div class='prd-txt-loader lds-dual-ring'></div>");
	}
	
	function removeProductLoader() { 
		$(".prd-txt-loader").remove();
	}
	
	function pushEmptyRow() {
		var recId = PUBLIC_BILL_DETAIL_LIST.length + 1		
		var emptyRow = {sno: recId, prdOrgId: "", name: "", qty : "", price : 0.00};
		PUBLIC_BILL_DETAIL_LIST.push(emptyRow);
		$("#billItems").append(getSingleBillRow(emptyRow));
		
		$("#inProductName" + recId).blur(function(){
			$("#billingForm").removeClass('was-validated');
			if ($(this).val() != "") {
				$("#inQty" + recId).attr("required", "required");
			} else {
				$("#inQty" + recId).removeAttr("required");
			}
			addNewRow(recId);
		});
	
		$("#inQty" + recId).blur(function(){
			$("#billingForm").removeClass('was-validated');
			if ($(this).val() != "") {
				$("#inProductName" + recId).attr("required", "required");
			} else {
				$("#inProductName" + recId).removeAttr("required");
			}
			addNewRow(recId);
			priceCalculation(recId);
//			if ($("#inProductName" + recId).val() != "") {
//				focusObject($("#inProductName" + (+recId + 1)));
//			}
		});
		
		$("#linkRemove" + recId).click(function() {
			var currentRecord = PUBLIC_BILL_DETAIL_LIST[recId - 1];
			
			var curPrdName = currentRecord.name;
			var curQty = currentRecord.qty;
			
			if ((curPrdName !="" && curPrdName != undefined) || (curQty != "" && curQty != undefined)) {
				$("#billRowItem" + recId).addClass("row-highlight");
				setTimeout(function() {
					if (confirm("Are you sure you want to delete the selected item?")) {
						updateBillItemArray(recId, "", "", "", "");
						$("#billRowItem" + recId).remove();
						updateSno(recId);
						updateTotal();
						showAlert("#cust-prod-delete");
					}
					$("#billRowItem" + recId).removeClass("row-highlight");
				}, 10);
			} else {
				alert("OOPS, You can't delete empty record.")
			}
		});
		
		var $objProductName = $("#inProductName" + recId);
		$objProductName.typeahead({
			minLength: 3,
			autoSelect: false,
			source:  function (query, process) {
				return $.ajax({
					url: '/product/getSearchList?q=' + query,
					dataType: "json",
					async : true,
					beforeSend: function() {
						setProductLoader("#inProductName" + recId);
					},
					success: function(data) {
						data.push({id: "-2", name: "Can't find? Add new prodcut!"});
						return process(data);
					},
					error: function(jqXHR, textStatus, errorThrown) {
						return process([{id: "-1", name: "Service unavailable, try again or contact administrator"}]);
					},
					complete: function() {
						removeProductLoader();
					}
				});
			},		
			matcher: function (item) { return true },
			displayText: function (item) {
				if (item.id > 0)
					return item.name + " (" + item.code +")";
				else
					return item.name;
			},
			afterSelect: function (current) {
				if (current){
					if (current.id == "-1" ) { //Error
						// $objProductName.val("");
						return false;
					} else if (current.id == "-2" ) { // Add new product
						setTimeout(() => {
							$objProductName.val("");
						}, 10);
						resetProductForm();
						$("#hiddenProductRowId").val(recId);
						$("#productModal").modal();
						return false;
					} else {
						var $vObjCodeList = $(".cls" + current.code);
						if ($vObjCodeList.length <= 0) {
							updateProdcutDetails (recId, current.name, current.code, "1", current.value, current.id);
						} else {
							$objProductName.val("");
							current = "";
							setTimeout(() => {
								$vObjCodeList.first().select();
								$vObjCodeList.first().focus();
							}, 10);
							showAlert("#cust-prod-exist");
						}
					}
				}
			}
		});
		
	}
	
	pushEmptyRow();
	
	function updateProdcutDetails (pRecId, pName, pCode, pQty, pPrice, pPrdOrgId) {
		$("#inQty" + pRecId).val(pQty);
		
		setTimeout(() => {
			$("#inQty" + pRecId).select();
			$("#inQty" + pRecId).focus();
			$("#inQty" + pRecId).addClass("cls" + pCode);
		}, 10);
			
		$("#spanPrice" + pRecId).html(pPrice);
		$("#inPrice" + pRecId).val(pPrice);
		updateBillItemArray(pRecId, pName, pQty, pPrice, pPrdOrgId);
	}
	
	function addNewRow(recId) {
		var currentRecord = PUBLIC_BILL_DETAIL_LIST[recId - 1];
		currentRecord.name = $("#inProductName" + recId).val();
		currentRecord.qty = $("#inQty" + recId).val();
		currentRecord.price = $("#inPrice" + recId).val();
		
		if (PUBLIC_BILL_DETAIL_LIST.length > 0) {
			var billLastRecord = PUBLIC_BILL_DETAIL_LIST[PUBLIC_BILL_DETAIL_LIST.length - 1];
			if ((billLastRecord.name !="" && billLastRecord.name != undefined) || (billLastRecord.qty != "" && billLastRecord.qty != undefined)) {
				pushEmptyRow();
				if (currentRecord.qty != "") {
					focusObject($("#inProductName" + (+recId + 1)));
				}
			}
		} else {
			pushEmptyRow();
		}
	}
	
	
	function priceCalculation(recId) {
		var eachValue = $("#inPrice" + recId).val();
		var qty = $("#inQty" + recId).val(); 
		
		if (qty !="" && qty != undefined && eachValue != "" && eachValue != undefined) {
			var priceValue = eachValue * qty;
			if ($.isNumeric(priceValue)) {
				$("#spanPrice" + recId).html(roundOfNum(priceValue));
				updateBillItemArrayPrice(recId, priceValue);
			}
		}
	}
	
	function updateBillItemArray(pRecId, pProductName, pQty, pPrice, pPrdOrgId) {
		var currentRecord = PUBLIC_BILL_DETAIL_LIST[pRecId - 1];
		currentRecord.name = pProductName;
		currentRecord.qty = pQty;
		currentRecord.price = pPrice;
		currentRecord.prdOrgId = pPrdOrgId;
		updateTotal();
	}
	
	function updateBillItemArrayPrice(pRecId, pPrice) {
		var currentRecord = PUBLIC_BILL_DETAIL_LIST[pRecId - 1];
		currentRecord.price = pPrice;
		updateTotal();
	}
	
	function updateTotal() {
		var billObject;
		var totPrice = 0;
		var totQty = 0;
		for (cntI=0; cntI < PUBLIC_BILL_DETAIL_LIST.length; cntI++) {
			billObject = PUBLIC_BILL_DETAIL_LIST[cntI];
			totPrice += (+billObject.price);
			totQty += (+billObject.qty);
		}
		
		if ($.isNumeric(totPrice)) {
			var cgstValue = totPrice * CGST_PERCENT /100;
			var sgstValue = totPrice * SGST_PERCENT /100;
			var grandTotal = (+totPrice) + (+cgstValue) + (+sgstValue);
			
			$("#spanTotalPrice").html(roundOfNum(totPrice));
			$("#spanCGST").html(roundOfNum(cgstValue));
			$("#spanSGST").html(roundOfNum(sgstValue));
			$("#spanGrandTotalPrice").html(roundOfNum(grandTotal));
			$("#hiddenTotalQty").val(totQty);
		}
	}
	
	function roundOfNum(pValue) {
		return parseFloat(pValue).toFixed(2);
	}
	
	function updateSno(pRecId) {
		$(".clsSno").each(function( index ) {
			  $(this).html((+index) + 1);
		});
	}
	
	function focusObject(pObject) {
		$(pObject).focus();
	}
	
	/* Product addition */
	$("#btnProductSave").click(function(event) {
	    var form = $("#productForm");
	    
	    if (form[0].checkValidity()) {
	    	event.preventDefault();
	    	event.stopPropagation();
	    	var dataJson = { 
	    			code: $("#inputCode").val(), 
	    			name : $("#formProductName").val(), 
	    			desc : $("#inputProductDesc").val(),
	    			messurement : $("#inputProductMeasurement").val(),
	    			type : $("#inputProductType").val(),
	    			value : $("#inputProductValue").val(),
	    			isActive : $('#isProductActive').is(":checked"),
	    			createdBy : 'ADMIN'
			};
	    	
	    	$.ajax({
	    		type: 'post',
	    		url: '/product/add',
	    		contentType: "application/json; charset=utf-8",
	            dataType   : "json",
				data: JSON.stringify(dataJson),
				async : true,
				beforeSend: function() {
					$("#pageLoadingContainer").show();
				},
				success: function(data) {
					var hiddenProductRowId = $("#hiddenProductRowId").val();
					updateProdcutDetails (hiddenProductRowId, dataJson.name, dataJson.code, "1", dataJson.value, data);
					$("#inProductName" + hiddenProductRowId).val(dataJson.name + " (" + dataJson.code + ")");
					$("#productModal").modal('hide');
					showAlert("#prod-add-success");
				},
				error: function(jqXHR, textStatus, errorThrown) {
					if (jqXHR.status == "409" ) {
						alert("Product code already exist");
					} else {
						alert("OOPS, Something went wrong! Please try again or contact administrator");
					}
				},
				complete: function() {
					$("#pageLoadingContainer").hide();
				}
	    	});
	    }
	    form.addClass('was-validated');
	});
	
	function productValidation() {
		var returnFlag = true;
		for (cntI=0; cntI < PUBLIC_BILL_DETAIL_LIST.length; cntI++) {
			eachBillItem = PUBLIC_BILL_DETAIL_LIST[cntI];
			if (eachBillItem.name != "" && eachBillItem.name != undefined && eachBillItem.price <= 0) {
				$("#invalid-product-" + eachBillItem.sno).show();
				returnFlag = false;
			} else {
				$("#invalid-product-" + eachBillItem.sno).hide();
			}
		}
		return returnFlag;
	}
	$("#btnSaveAndPrint").click(function() {
		var form = $("#billingForm");
		
	    if (form[0].checkValidity() && productValidation()) {
	    	event.preventDefault();
	    	event.stopPropagation();
	    
			var valCustomerId = $("#hiddenCustomerId").val();
			
			var valTotItems = $("#hiddenTotalQty").val();
			var valTotValue = $("#spanTotalPrice").html();
			var valGstStatePercent = SGST_PERCENT;
			var valGstCentralPercent = CGST_PERCENT;
			var valGstStateVal = $("#spanSGST").html();
			var valGstCentralVal = $("#spanCGST").html()
			var valTotal = $("#spanGrandTotalPrice").html();
			var valCreatedBy = 'ADMIN';
			
			var jsonBilling = {
					customer: { id : valCustomerId},
					totItems: valTotItems,
					totValue: valTotValue,
					gstStatePercent: valGstStatePercent,
					gstCentralPercent: valGstCentralPercent,
					gstStateVal: valGstStateVal,
					gstCentralVal: valGstCentralVal,
					total: valTotal,
					createdBy: valCreatedBy,
					billingItems : [{}]
			};
			
			var jsonBillingItems = [];
			
			var valProductId ="";
			var valQty ="";
			var valValue = "";
			var eachBillItem;
			var jsonEachBillItem;
			var nameValidation  = "";
			for (cntI=0; cntI < PUBLIC_BILL_DETAIL_LIST.length; cntI++) {
				eachBillItem = PUBLIC_BILL_DETAIL_LIST[cntI];
				if (eachBillItem.name != "" && eachBillItem.name != undefined) {
					valQty = eachBillItem.qty;
					valValue = eachBillItem.price;
					valProductId = eachBillItem.prdOrgId;
					nameValidation  += eachBillItem.name;
					jsonEachBillItem = {
							product: {id: valProductId},
							productName: eachBillItem.name,
							qty: valQty,
							value: valValue,
							createdBy: 'ADMIN'
					};
					jsonBillingItems.push(jsonEachBillItem);
				}
			}
			jsonBilling.billingItems = jsonBillingItems;
			if (nameValidation == "") {
				alert("Oops, It looks like you have submitted epmty form!");
			} else {
				$.ajax({
		    		type: 'post',
		    		url: '/billing/add',
		    		contentType: "application/json; charset=utf-8",
		            dataType   : "json",
					data: JSON.stringify(jsonBilling),
					async : false,
					beforeSend: function() {
						$("#pageLoadingContainer").show();
					},
					success: function(data) {
						printBilling(data, jsonBilling);
						resetPage();
						showAlert("#billing-success");
						scrollToTop();
						$("#pageLoadingContainer").hide();
					},
					error: function(jqXHR, textStatus, errorThrown) {
						if (jqXHR.status == "200" ) {
							showAlert("#billing-success");
							resetPage();
						} else {
							alert("OOPS, Something went wrong! Please try again or contact administrator");
						}
					},
					complete: function() {
						
					}
		    	});
		    }
	    }
		form.addClass('was-validated');
	});
	
	function resetPage() {
		$("#inNameOrPhone").val("");
		$("#cust-address").html("");
		
		$("#profile-img").attr("src" , "assets/img/hm-user.png")
		$("#profile-img").hide();
		
		$("#billItems").html("");
		PUBLIC_BILL_DETAIL_LIST = [];
		
		pushEmptyRow();
		
		$("#spanTotalPrice").html("0.00");
		$("#spanCGST").html("0.00");
		$("#spanSGST").html("0.00");
		$("#spanGrandTotalPrice").html("0.00");
		$("#hiddenTotalQty").val("0.00");
	}
	
	$("#btnClear").click(function(event) {
		if (confirm("Are you sure you want to clear all form?")) {
			resetPage();
			scrollToTop();
		}
	});
	
	function showAlert(pObject) {
		$(pObject).fadeTo(5000, 600).slideUp(600, function(){
			$(pObject).slideUp(600);
        });
	}
	
	function roundOf2Digit(pValue) {
		if (pValue != "" && pValue != undefined && pValue != null) {
			var retunValue = parseFloat(pValue).toFixed(2);
			return retunValue.split(".00").join("");
		} else {
			return "";
		}
	}
	
	function printBilling(billingId, jsonBilling) {
		var myWindow = window.open('/print?bid=' + billingId, '_blank', 'width=560,height=396');
	    myWindow.focus();
	    //myWindow.print();
	    //myWindow.close();
	}
	
	function scrollToTop() {
		$('html,body').animate({ scrollTop: 0 }, 'slow');
	}
});