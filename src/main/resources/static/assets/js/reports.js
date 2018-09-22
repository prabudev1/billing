
$(document).ready(function() {	
	
	var response = [];
	var paramFromDate = GetParameterValues("fd");
	var paramToDate = GetParameterValues("td");
	var orderVal = GetParameterValues("o");
	var listView = GetParameterValues("lv");
	
	var startDate = moment().subtract(30, 'days');
    var endDate = moment();

	if (paramFromDate != undefined && paramFromDate != "" && paramToDate != undefined && paramToDate != "") {
		startDate = moment(paramFromDate);
	    endDate = moment(paramToDate);
	}
	dateRangePickerInit(startDate, endDate);
	
	if (orderVal == undefined) {
		orderVal = "1"; // Recently added first
	}
	
	if (listView == undefined) {
		listView = "1"; // Recently added first
	}
	
	$("#billOrderBy").val(orderVal);
	$("#listView").val(listView);

    function dateRangePickerInit(start, end) {
        $('#from-to-date-picker span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
        $("#hidden-date-from").val(start.format('YYYY-MM-DD') + " 00:00:00");
        $("#hidden-date-to").val(end.format('YYYY-MM-DD') + " 23:59:59");
    }
    
    function onDateRangePicker(start, end) {
    	dateRangePickerInit(start, end);
    	onSearchOrder();
    }
    
    $('#from-to-date-picker').daterangepicker({
        startDate: startDate,
        endDate: endDate,
        
        opens: "right",
        drops: "down",
        autoApply: false,
        ranges: {
            'Today': [moment(), moment()],
            'Last 7 Days': [moment().subtract(6, 'days'), moment()],
            'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        }
    }, onDateRangePicker);
    
    
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
            	var dtFrom = $("#hidden-date-from").val();
            	var dtTo = $("#hidden-date-to").val();
                
            	$.ajax({
    	    		type: 'get',
    	    		url: '/billing/getBillingList?fd=' + dtFrom + "&td=" + dtTo + "&o=" + orderVal,
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
    		var dateStr = moment(item.createdOn).format("DD-MMM-YYYY");
    		if ($("#listView option:selected").val() == 2) {
    			var displayName = "NA";
        		if (item.customer != null) {
        			displayName = (item.customer.name + " (" + item.customer.mobile +")");
        		}
	        	var $info = $("<tr>")
	            	.append($("<td>").addClass("jsgrid-cell text-center").text(item.id))
	            	.append($("<td>").addClass("jsgrid-cell text-left").text(displayName))
	            	.append($("<td>").addClass("jsgrid-cell text-right").text(item.totItems))
	            	.append($("<td>").addClass("jsgrid-cell text-right").text(item.totValue))
	                .append($("<td>").addClass("jsgrid-cell text-right").text(item.gstStateVal))
	                .append($("<td>").addClass("jsgrid-cell text-right").text(item.gstCentralVal))                    			
	            	.append($("<td>").addClass("jsgrid-cell text-right").text(item.total))
	            	.append($("<td>").addClass("jsgrid-cell text-center").text(dateStr));
	            return $info;
    		} else {
    			var $billPproductList;
    			var billRec;
    			
    			var $billHeaderColumns = $("<thead>")
				.append($("<tr>")
    			.append($("<td>").addClass("jsgrid-header-cell text-center").text("S.No"))
    	    	.append($("<td>").addClass("jsgrid-header-cell text-left tbl-report-name").text("Product Name"))
    	    	.append($("<td>").addClass("jsgrid-header-cell text-right").text("Rate"))
    	    	.append($("<td>").addClass("jsgrid-header-cell text-right").text("Qty"))
    	    	.append($("<td>").addClass("jsgrid-header-cell text-center").text("Amount")));
	
    			var $billPproductList = $("<tbody>");
    			
    			for (i = 0; i < item.billingItems.length; i++) {
                    billRec = item.billingItems[i];
                    var prdName = "NA";
                	var prdValue = "NA";
                	if (billRec.product != null) {
                		prdName = billRec.product.name;
                		prdValue = billRec.product.value;
                	}
                	
                	$billPproductList.append($("<tr>")
                	.append($("<td>").addClass("jsgrid-cell text-center").text( ((+i) + 1)))
                	.append($("<td>").addClass("jsgrid-cell text-left").text(prdName))
                	.append($("<td>").addClass("jsgrid-cell text-right").text(prdValue))
                	.append($("<td>").addClass("jsgrid-cell text-right").text(billRec.qty))
                    .append($("<td>").addClass("jsgrid-cell text-right").text(roundOf2Digit(billRec.value))));
                }
    			    			
    			var $info = $("<div>").addClass("list-info list-bill-info")
            	
                .append($("<div>").addClass("form-row")
        		.append($("<div>").addClass("col-md-2")
				.append($("<p>").addClass("list-bill-heading").text("Bill No:"))
        		.append($("<p>").html(item.id))
        		.append($("<p>").addClass("list-bill-heading").text("Invoice Date:"))
        		.append($("<p>").html(dateStr)))

            	.append($("<div>").addClass("col-md-3")
        		.append($("<p>").addClass("list-bill-heading").text("Buyer Info:"))
        		.append($("<p>").html(getCustInfo(item.customer))))
            	
                .append($("<div>").addClass("col-md-7")
                .append($("<p>").addClass("list-bill-heading").text("Product Items:"))
                .append($("<table>").append($billHeaderColumns).append($billPproductList))))
                
                .append($("<div>").addClass("form-row")
        		.append($("<div>").addClass("col-md-2 desktop-print")
				.append($("<div>").addClass("report-icon report-icon-print").append($("<span>").addClass("fa fa-print")).attr("binding-id", item.id)))

            	.append($("<div>").addClass("col-md-3")
        		.append($("<p>").addClass("list-bill-heading").text("")))
            	
                .append($("<div>").addClass("col-md-7 lb-sub-container")
                .append($("<span>").addClass("lb-sub-hd").html("Sub Total:"))
                .append($("<span>").addClass("lb-sub-hd-val").text(item.totValue))
                
                .append($("<span>").addClass("lb-sub-hd").html("SGST: "))
                .append($("<span>").addClass("lb-sub-hd-val").text(item.gstStateVal))
                
                .append($("<span>").addClass("lb-sub-hd").html("CGST: "))
                .append($("<span>").addClass("lb-sub-hd-val").text(item.gstCentralVal))
                
                .append($("<span>").addClass("lb-sub-hd").html("Grand Total (₹): "))
                .append($("<span>").addClass("lb-sub-hd-val").text(item.total))))
                
                .append($("<div>").addClass("form-row mobile-print")
        		.append($("<div>").addClass("col-md-2")
				.append($("<div>").addClass("report-icon report-icon-print").append($("<span>").addClass("fa fa-print")).attr("binding-id", item.id))))

                
            return $("<tr>").append($("<td>").append($info));
    		}
        },
        rowClick: function (args) {
        	
        },
        onDataLoaded: function (args) {
        	
        },     // on done of controller.loadData
        onRefreshed: function(args) {
        	addHeader();
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
	
	function addHeader(item) {
		if ($("#listView option:selected").val() == 2) {
			var $info = $("<tr>")
	    	.append($("<td>").addClass("jsgrid-header-cell text-center").text("Bill No"))
	    	.append($("<td>").addClass("jsgrid-header-cell text-left tbl-report-name").text("Buyer Name"))
	    	.append($("<td>").addClass("jsgrid-header-cell text-right").text("Qty"))
	    	.append($("<td>").addClass("jsgrid-header-cell text-right").text("Sub Total"))
	        .append($("<td>").addClass("jsgrid-header-cell text-right").text("SGST"))
	        .append($("<td>").addClass("jsgrid-header-cell text-right").text("CGST"))                    			
	    	.append($("<td>").addClass("jsgrid-header-cell text-right").text("Grand Total (₹)"))
	    	.append($("<td>").addClass("jsgrid-header-cell text-center").text("Generated On"));
			$(".jsgrid-table tbody").prepend($info);
		}
		
		$(".report-icon-print").click(function () {
			var billingId = $(this).attr("binding-id");
			var myWindow = window.open('/print?bid=' + billingId, '_blank', 'width=560,height=396');
		});
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
	
	$("#billOrderBy").change(function () {
		onSearchOrder();
	});
	
	$("#listView").change(function () {
		onSearchOrder();
	});
	
	function onSearchOrder() {
		var orderIndex = $("#billOrderBy option:selected").val();
		var listView = $("#listView option:selected").val();
		var fromDate = $("#hidden-date-from").val();
	    var toDate = $("#hidden-date-to").val();
	    window.location.href = "reports?fd=" + fromDate + "&td=" + toDate + "&o=" + orderIndex + "&lv=" + listView;
	}
	
	$("#rpt-reload").click(function(){
		window.location.href = "reports";
	});
	
	$("#rpt-excel").click(function(){
		var orderIndex = $("#billOrderBy option:selected").val();
		var listView = $("#listView option:selected").val();
		var fromDate = $("#hidden-date-from").val();
	    var toDate = $("#hidden-date-to").val();
	    window.open("billing/downloadExcel?fd=" + fromDate + "&td=" + toDate + "&o=" + orderIndex + "&lv=" + listView, '_blank');
	});
	
	function roundOf2Digit(pValue) {
		if (pValue != "" && pValue != undefined && pValue != null) {
			var retunValue = parseFloat(pValue).toFixed(2);
			return retunValue.split(".00").join("");
		} else {
			return "";
		}
	}
	function appendAddress(fullLine, addLine, cancatenateChar) {
		if (addLine != "" && addLine != null) {
			fullLine += cancatenateChar + addLine; 
		}
		return fullLine;
	}
	function getCustInfo(custItem) {
		var custInfo = "NA";
		if (custItem != null) {
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
			
		}
		return custInfo;
	}
	
	$("#pageLoadingContainer").hide();
});