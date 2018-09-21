
$(document).ready(function() {
	
	$("#pageLoadingContainer").hide();
	
	var response = [];
	var searchVal = GetParameterValues("q");
	var orderVal = GetParameterValues("o");
	
	if (searchVal == undefined) {
		searchVal = "";
	}
	if (orderVal == undefined) {
		orderVal = "6"; // Recently added first
	}
	
	$("#custNameMobile").val(searchVal);
	$("#custOrderBy").val(orderVal);
	
	function showAlert(pObject) {
		$(pObject).fadeTo(5000, 600).slideUp(600, function(){
			$(pObject).slideUp(600);
        });
	}
	
	var grid = $("#cust-managment-grid").jsGrid({
        width: "100%",
        editing: true,
        sorting: true,
        paging: true,
        autoload: true,
        pageSize: 20,
        // pagerFormat: "Page {pageIndex} of {pageCount} &nbsp;&nbsp; {first} {prev} {pages} {next} {last} &nbsp;&nbsp; total records: {itemCount}",
        pagerFormat: "{first} {prev} {pages} {next} {last}",
        pagePrevText: "<",
        pageNextText: ">",
        pageFirstText: "<<",
        pageLastText: ">>",
        pageNavigatorNextText: "&#8230;",
        pageNavigatorPrevText: "&#8230;",
        pagerContainerClass: "pagination pagination-div",
        pagerClass: "pagination pagination-div",
        pagerNavButtonClass: "page-link",
        pagerNavButtonInactiveClass: "page-link disabled",
        pageClass: "page-link",
        currentPageClass: "btn btn-primary active",
        controller: {
            loadData: function (filter) {
            	
            	$.ajax({
    	    		type: 'get',
    	    		url: '/customer/getAll?q=' + searchVal + "&o=" + orderVal,
    	    		contentType: "application/json; charset=utf-8",
    	            dataType   : "json",
    				async : false,
    				beforeSend: function() {
    					$("#pageLoadingContainer").show();
    				},
    				success: function(data) {
    					response = data;
    				},
    				error: function(jqXHR, textStatus, errorThrown) {
    					alert("OOPS, Something went wrong! Please try again or contact administrator");
    				},
    				complete: function() {
    					$("#pageLoadingContainer").hide();
    				}
    	    	});
 
                return	response;
            },
            insertItem: function (item) {
                
            },
            updateItem: function (item) {
                
            }
        },
        rowRenderer: function(item) {
        	var formattedDate = new Date(item.createdOn);
        	var dateStr = moment(item.createdOn).format("dddd, MMMM Do YYYY, HH:mm:ss");
        	if (dateStr == "Invalid date" || dateStr == undefined) {
        		dateStr = "";
        	}
        	var varAadharNo = item.aadhar;
        	if (varAadharNo == null || varAadharNo == "" || varAadharNo == undefined) {
        		varAadharNo = "NA";
        	}
        	var varGstinNo = item.gstin;
        	if (varGstinNo == null || varGstinNo == "" || varGstinNo == undefined) {
        		varGstinNo = "NA";
        	}
        	var varEmail = item.email;
        	if (varEmail == null || varEmail == "" || varEmail == undefined) {
        		varEmail = "NA";
        	}
        	var varMobile = item.mobile;
        	if (varMobile == null || varMobile == "" || varMobile == undefined) {
        		varMobile = "NA";
        	}
            var $info = $("<div>").addClass("list-info")
            	
            	.append($("<div>").addClass("row")
            	.append($("<div>").addClass("col-12")
                .append($("<p>").append($("<strong>").text(item.name)))))
                
                .append($("<div>").addClass("row")
            	.append($("<div>").addClass("col-3")
                .append($("<p>").addClass("list-email-info").append( ($("<span>").text("EMAIL:"))).append(" " + varEmail))
                .append($("<p>").append( ($("<span>").text("MOBILE:"))).append(" " + varMobile)))
            			
                
                .append($("<div>").addClass("col-3")
                .append($("<p>").addClass("list-email-info").append( ($("<span>").text("AADHAR:"))).append(" " + varAadharNo))
                .append($("<p>").append( ($("<span>").text("GSTIN:"))).append(" " + varGstinNo)))                      
                		
                .append($("<div>").addClass("col-3")
                .append($("<p>").text(item.addressLine1 + ", " + item.addressLine2))
                .append($("<p>").text(item.city + ", " + item.state + ", " + item.pinCode))))
                        
                .append($("<div>").addClass("row")
            	.append($("<div>").addClass("col-12")
                .append($("<p>").addClass("list-time-info").append(dateStr))));
                
            return $("<tr>").append($("<td>").append($info));
        },
        fields: [
        	  
        ],
        rowClick: function (args) {
        	showEditDialog(args.item);
        },
        onDataLoaded: function (args) {
        	trimCustomerInfo();
        },     // on done of controller.loadData
        onRefreshed: function(args) {
        	trimCustomerInfo();
        },
        onInit: function (args) {   },           // after grid initialization 
        onItemInserting: function (args) { },  // before controller.insertItem
        onItemInserted: function (args) {
            $("#usersCount").text(args.grid.data.length);

        },   // on done of controller.insertItem
        onItemUpdating: function (args) { },   // before controller.updateItem
        onItemUpdated: function (args) { },    // on done of controller.updateItem
        onItemDeleting: function (args) { },   // before controller.deleteItem
        onItemDeleted: function (args) { }    // on done of controller.deleteItem
    }).data("JSGrid");
	
	function trimCustomerInfo() {
		$( ".list-info" ).each(function( index ) {
    		var trimedInfo = $(this).html(); //<p>, </p>
            trimedInfo = trimedInfo.split(", , ").join(", ");
            trimedInfo = trimedInfo.split("<p></p>").join("");
            trimedInfo = trimedInfo.split("<p>, </p>").join("");
            trimedInfo = trimedInfo.split("<p>, ").join("<p>");
            trimedInfo = trimedInfo.split(", </p>").join("");
            $(this).html(trimedInfo);
    	});
	}
	
	function showEditDialog(paramItem) {
		$("#inputName").val(paramItem.name); 
		$("#inputEmail").val(paramItem.email); 
		$("#inputMobile").val(paramItem.mobile);
		$("#inputAadhar").val(paramItem.aadhar);
		$("#inputGstin").val(paramItem.gstin);
		$("#inputType").val(paramItem.type);
		
		if (paramItem.dob != null) {
			$('#inputDob').val(moment(paramItem.dob).format("DD-MMM-YYYY"));
			$("#hiddenDob").val(moment(paramItem.dob).format('YYYY-MM-DD'));
		}
		
		$("#inputGender").val(paramItem.gender);
		$("#inputAddress").val(paramItem.addressLine1);
		$("#inputAddress2").val(paramItem.addressLine2);
		$("#inputCity").val(paramItem.city);
		$("#inputState").val(paramItem.state);
		$("#inputZip").val(paramItem.pinCode);		
		
		if (paramItem.activeFlag == true) {
			$('#isActive').attr('checked', true);
		} else {
			$('#isActive').attr('checked', false);
		}
		
		$("#hiddenCustId").val(paramItem.id);
		$("#customerModal").modal();
	}
	
	$(".add-new-customer").click(function(){
		resetForm();
		$("#hiddenCustId").val("-1");
		$("#customerModal").modal();
	});
	
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
				activeFlag : $('#isActive').is(":checked"),
				createdBy : 'ADMIN',
			};
	    	
	    	var varURL = "/customer/add/";
	    	if ($("#hiddenCustId").val() > 0) {
	    		varURL = "/customer/update/";
	    		dataJson.id = $("#hiddenCustId").val(); 
	    	}
	    	$.ajax({
	    		type: 'post',
	    		url: varURL,
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
		$("#customerModal").modal('hide');
		showAlert("#cust-success-alert");
		setTimeout(() => {
			window.location.href = "customers";
		}, 700);
	}
	
	function GetParameterValues(param) {  
        var url = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');  
        for (var i = 0; i < url.length; i++) {  
            var urlparam = url[i].split('=');  
            if (urlparam[0] == param) {  
                return urlparam[1];  
            }  
        }  
    }
	
	$("#custOrderBy").change(function () {
		onSearchOrder();
	});
	
	$('#custNameMobile').keypress(function(event){
	    var keycode = (event.keyCode ? event.keyCode : event.which);
	    if(keycode == '13'){
	    	onSearchOrder(); 
	    }
	});
	
	function onSearchOrder() {
		var orderIndex = $("#custOrderBy option:selected").val();
	    var searchVal = $("#custNameMobile").val();
	    window.location.href = "customers?q=" + searchVal + "&o=" + orderIndex;
	}
	
	$('#inputDob').daterangepicker({
		singleDatePicker: true,
    	showDropdowns: true,
    	minYear: 1901,
    	maxYear: parseInt(moment().format('YYYY'), 10),
    	locale: {format: 'DD-MMM-YYYY'}
	}, function(start, end, label) {
		$("#hiddenDob").val(start.format('YYYY-MM-DD'));
	});	
	
	$('#inputDob').val("");
	
	$(".reload-customers").click(function(){
		window.location.href = "customers";
	});
	
	function resetForm () {
		$("#inputName").val(""); 
		$("#inputEmail").val(""); 
		$("#inputMobile").val("");
		$("#inputAadhar").val("");
		$("#inputGstin").val("");
		$("#inputType").val("");
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
});