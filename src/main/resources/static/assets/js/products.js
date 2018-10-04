
$(document).ready(function() {
	
	$("#pageLoadingContainer").hide();
	
	var response = [];
	var searchVal = GetParameterValues("q");
	var orderVal = GetParameterValues("o");
	
	if (searchVal == undefined) {
		searchVal = "";
	}
	if (orderVal == undefined) {
		orderVal = "8"; // Recently added first
	}
	
	$("#prdNameMobile").val(searchVal);
	$("#prdOrderBy").val(orderVal);
	
	function showAlert(pObject) {
		$(pObject).fadeTo(5000, 600).slideUp(600, function(){
			$(pObject).slideUp(600);
        });
	}
	
	var grid = $("#prd-managment-grid").jsGrid({
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
    	    		url: '/product/getAll?q=' + searchVal + "&o=" + orderVal,
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
        	
        	var valHSN = nullToString(item.hsn, "Unavailable");
        	var valMessurement = nullToString(item.messurement, "Unavailable");
        	var valDesc = nullToString(item.desc, "");
        	var valType = nullToString(item.type, "Unavailable");
        	
            var $info = $("<div>").addClass("list-info")
                .append($("<p>").append($("<strong>").text(item.code + " - " + item.name)))
                .append($("<p>").text(valDesc))
                .append($("<p>").addClass("list-email-info").append( ($("<span>").text("HSN/SAC: "))).append(valHSN))
                .append($("<p>").append( ($("<span>").text("Type: "))).append(valType))
                .append($("<p>").append( ($("<span>").text("Messurement: "))).append(valMessurement))
                .append($("<p>").append( ($("<span>").text("Value: "))).append(item.value))
                .append($("<p>").addClass("list-time-info").append(dateStr));
                
            return $("<tr>").append($("<td>").append($info));
        },
        fields: [
        	  
        ],
        rowClick: function (args) {
        	showEditDialog(args.item);
        },
        onDataLoaded: function (args) {
        	trimProductInfo();
        },     // on done of controller.loadData
        onRefreshed: function(args) {
        	trimProductInfo();
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
	
	function trimProductInfo() {
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
	
	$(".add-new-product").click(function(){
		resetProductForm();
		$("#hiddenPrdId").val("-1");
		$("#productModal").modal();
	});
	
	function GetParameterValues(param) {  
        var url = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');  
        for (var i = 0; i < url.length; i++) {  
            var urlparam = url[i].split('=');  
            if (urlparam[0] == param) {  
                return urlparam[1];  
            }  
        }  
    }
	
	$("#prdOrderBy").change(function () {
		onSearchOrder();
	});
	
	$('#prdNameMobile').keypress(function(event){
	    var keycode = (event.keyCode ? event.keyCode : event.which);
	    if(keycode == '13'){
	    	onSearchOrder(); 
	    }
	});
	
	function onSearchOrder() {
		var orderIndex = $("#prdOrderBy option:selected").val();
	    var searchVal = $("#prdNameMobile").val();
	    window.location.href = "products?q=" + searchVal + "&o=" + orderIndex;
	}
	
	
	$(".reload-products").click(function(){
		window.location.href = "products";
	});
	
	$("#btnProductSave").click(function(event) {
	    var form = $("#productForm");
	    
	    if (form[0].checkValidity()) {
	    	event.preventDefault();
	    	event.stopPropagation();
	    	var dataJson = { 
	    			code: $("#inputCode").val(), 
	    			name : $("#formProductName").val(), 
	    			desc : $("#inputProductDesc").val(),
	    			desc : $("#inputProductDesc").val(),
	    			hsn : $("#inputProductHsn").val(),
	    			type : $("#inputProductType").val(),
	    			value : $("#inputProductValue").val(),
	    			activeFlag : $('#isProductActive').is(":checked")
			};
	    	var varURL = "/product/add/";
	    	if ($("#hiddenPrdId").val() > 0) {
	    		varURL = "/product/update/";
	    		dataJson.id = $("#hiddenPrdId").val(); 
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
					$("#productModal").modal('hide');
					showAlert("#prod-add-success");
					setTimeout(() => {
						window.location.href = "products";
					}, 700);
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
	
	function nullToString(pValue, defaultValue) {
		var retValue = pValue;
		if (pValue == "" || pValue == undefined || pValue == null || pValue == "null") {
			retValue = defaultValue;
		}
		return retValue;
	}
	
	function resetProductForm () {
		$("#hiddenProductRowId").val("");
		$("#inputCode").val(""); 
		$("#formProductName").val(""); 
		$("#inputProductDesc").val("");
		$("#inputProductHsn").val("");
		$("#inputProductMeasurement").val("");
		$("#inputProductType").val("");
		$("#inputProductValue").val("");	
		$('#isProductActive').attr('checked', true);
		$("#productForm").removeClass('was-validated');
	}
	
	function showEditDialog(paramItem) {
		$("#hiddenPrdId").val(paramItem.id);
		$("#formProductName").val(paramItem.name);
		$("#inputCode").val(paramItem.code);
		$("#inputProductHsn").val(paramItem.hsn);
		$("#inputProductDesc").val(paramItem.desc);
		$("#inputProductMeasurement").val(paramItem.messurement);
		$("#inputProductType").val(paramItem.type);
		$("#inputProductValue").val(paramItem.value);
		if (paramItem.activeFlag == true) {
			$('#isProductActive').attr('checked', true);
		} else {
			$('#isProductActive').attr('checked', false);
		}
		$("#productModal").modal();
	}
});