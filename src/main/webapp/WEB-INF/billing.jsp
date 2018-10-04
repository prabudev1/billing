
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="layout/head.jsp"></jsp:include>
	</head>
	<body>
		<div class="wrapper">
	        <jsp:include page="layout/menu.jsp">
					<jsp:param name="mnBillingHome" value="active" />
			</jsp:include>
	        
	        <div id="content">
				<jsp:include page="layout/header.jsp">
					<jsp:param name="pageName" value="Billing" />
				</jsp:include>
				
				<section class="sec-body">
					<div class="container billing-container">
						<div class="row">
							<div class="col-12">
								<h3>Customer Info</h3>
							</div>
						</div>
						<div class="row">
							<div class="col-6 col-custom">
								<div class="form-group" >
									<input type="text" id="inNameOrPhone" class="form-control typeAheadCustName input-lg" onClick="this.setSelectionRange(0, this.value.length)" 
											data-provide="typeahead" placeholder="Enter customer name or mobile" tabindex="1">
									<div class="txt-loader lds-dual-ring" style="display: none;"></div>
									<input type="hidden" class="form-control" id="hiddenCustomerId" >
								</div>
							</div>
							<div class="col-6 text-right col-custom">
								<span id="cust-address"></span>
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<h3>Product Info</h3>
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<form id="billingForm">
									<table class="table table-bordered tbl-bills">
										<thead>
											<tr>
												<th width="5%">S.No</th>
												<th width="65%">Product</th>
												<th width="15%">Qty</th>
												<th width="10%">Price</th>
												<th width="2%" class="tbl-bill-action"></th>
											</tr>
										</thead>
										<tbody id="billItems">
											
										</tbody>
										<tfoot>
											<tr>
												<td rowspan="5" colspan="2" class="tbl-bills-date" valign="bottom">
													<div class="">
														<label for="billingDate">Billing Date</label> 
														<input type="text"class="form-control col-6" id="billingDate">
														<input type="hidden" id="hiddenBillingDate" name="hiddenBillingDate">
													</div>
												</td>
											</tr>
											<tr>
												<td class="tbl-bills-hide">Total</td>
												<td class="tbl-bills-hide"><span id="spanTotalPrice">0.00</span></td>
											</tr>
											<tr>
												<td class="tbl-bills-hide">CGST <span id="spanCGSTPercent"></span>%</td>
												<td class="tbl-bills-hide"><span id="spanCGST">0.00</span></td>
											</tr>
											<tr>
												<td class="tbl-bills-hide">SGST <span id="spanSGSTPercent"></span>%</td>
												<td class="tbl-bills-hide"><span id="spanSGST">0.00</span></td>
											</tr>
											<tr>
												<td class="tbl-bills-hide">Grand Total</td>
												<td class="tbl-bills-hide"><span id="spanGrandTotalPrice">0.00</span></td>
											</tr>
											<tr>
												<td colspan="5" class="tbl-bill-btns">
													<button type="button" class="btn btn-primary" id="btnSaveAndPrint">Save & Print</button>
													<button type="button" class="btn btn-secondary" id="btnClear">Clear</button>
													<input type="hidden" id="hiddenTotalQty" value="">
													<div id="hiddenBillingRecords" style="display: none;"></div>
												</td>
											</tr>
										</tfoot>
									</table>
								</form>
							</div>
						</div>
					</div>
				</section>
				<jsp:include page="layout/footer.jsp"></jsp:include>
	        </div>
    	</div>
    	
		<div class="alert alert-success my-alert" id="cust-prod-delete" style="display: none;">
			<button type="button" class="close" data-dismiss="alert">x</button>
			<strong>Product</strong> item has been removed successfully.
		</div>
		
		<div class="alert alert-success my-alert" id="billing-success" style="display: none;">
			<button type="button" class="close" data-dismiss="alert">x</button>
			<strong>Billing </strong> have added successfully.
		</div>
		
		<jsp:include page="layout/modal-customer.jsp"></jsp:include>
		<jsp:include page="layout/modal-product.jsp"></jsp:include>
	</body>
	
	<script src="assets/js/main.js"></script>
	<script src="assets/js/billing.js"></script>
</html>
