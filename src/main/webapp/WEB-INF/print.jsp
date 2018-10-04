
<!DOCTYPE html>
<html>
	<head>
		<title>Gpware - Billing</title>
		<link href="assets/fonts/hind-madurai.css" rel="stylesheet">
		<script src="assets/js/jquery-3.3.1.min.js"></script>
		<script src="assets/js/date.moment.js"></script>
		<style type="text/css">
			* {
				padding: 0;
				margin: 0;
				box-shadow: none;
			}
			body {
				background: rgb(204, 204, 204);
				font-family: 'Hind Madurai', sans-serif;
				font-size: 10px;
				width: 5.845in;
			}
			table {
			    border-collapse: collapse;
			    width: 100%;
			    
			}
			td {
				vertical-align: top;
				
			}
			.print-master-table {
				width: 95%;
			    margin: 0 auto;
			}
			.print-product-table {
				padding: 0;
			}
			.print-container {
				background: white;
				display: block;
				margin: 0 auto;
				margin-bottom: 0.5cm;
				/* box-shadow: 0 0 0.5cm rgba(0, 0, 0, 0.5); */
    			height: 8.5in;
				width: 5.845in;
			    padding-bottom: 10px;
			    page-break-after: always;
			}
			
			.highlight {
				font-weight: bold;
			}
			.highlight-1 {
				font-style: italic;
			}
			.print-master-table td {
				border: 0.5px solid black;
			}
			.print-product-table td {
				border: none;
				padding-left: 3px;
				padding-right: 3px;
			}
			
			.print-product-table thead tr td {
				border-right: 0.5px solid black;
				border-bottom: 0.5px solid black;
				padding-top: 3px;
    			padding-bottom: 3px;
			}
			
			.print-product-table tbody tr td,
			.print-product-table tfoot tr td {
				border-right: 0.5px solid black;
				padding-top: 3px;
			}
			
			.print-product-table tr td:last-child {
				border-right: none;
			}
			.total-row td {
				border-top: 0.5px solid black;
				border-right: 0.5px solid black;
				border-bottom: 0.5px solid black;
			}
			
			.print-product-table tbody tr td,
			.print-product-table tfoot tr td {
				text-align: right;
			}
			.print-product-table tbody tr td:nth-child(1) {
				text-align: center;
			}
			.print-product-table tbody tr td:nth-child(2),
			.print-product-table tbody tr td:nth-child(3),
			.print-product-table tfoot tr:last-child td {
				text-align: left;
			}
			.tbl-title {
				border: none!important;
				text-align: center;
				font-weight: bold;
	 			padding-top: 10px;
	 			padding-bottom: 10px;
			}
			.prd-content, .cust-content {
				padding: 10px 5px 10px 5px;
			}
			.space-top-bt {
				padding-top: 5px;
    			padding-bottom: 5px;
			}
		</style>
	</head>
	<body>
		<div class="print-container" id="printDiv">
			<table class="print-master-table" >
				<tr>
					<td colspan="3" class="tbl-title">TAX INVOICE</td>
				</tr>
				<tr>
					<td width="70%" class="prd-content" >
						<span class="highlight" style="text-transform: uppercase; font-size: 150%;">${CONST_UI_USER_LOGIN_PRINT_NAME}</span><br> 
						${CONST_UI_USER_LOGIN_ADDR_HTML}
					</td>
					<td width="15%" class="prd-content">
						<span class="highlight">Invoice No:&nbsp;</span><br> 
						<span id="billNumber">NA</span>
					</td>
					<td width="15%" class="prd-content"> 
						<span class="highlight">Dated:&nbsp;</span><br> 
						<span id="billGenerated">NA</span>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="cust-content">
						<span class="highlight">Buyer</span><br>
						<span id="custInfo">NA</span> <br>
						<span class="highlight-1">GSTIN:&nbsp;</span><span id="custGSTIN">NA</span>,&nbsp; 
						<span class="highlight-1">AADHAR:&nbsp;</span><span id="custAADHAR">NA</span>
					</td>
				</tr>
				<tr>
					<td colspan="3" >
						<table class="print-product-table">
							<thead>
								<tr class="head-row">
									<td width="3%" class="highlight">S.No</td>
									<td width="60%" class="highlight">Product Name</td>
									<td width="15%" class="highlight">HSN</td>
									<td width="7%" class="highlight">Rate</td>
									<td width="7%" class="highlight">Qty</td>
									<td width="8%" class="highlight">Amount</td>
								</tr>
							</thead>
							<tbody id="billingRecords"></tbody>
							<tfoot>
								<tr class="empty-border"><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
								<tr class="empty-border">
									<td>&nbsp;</td>
									<td class="highlight">Sub Total:</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>Rs.</td>
									<td><span id="subTotal"></span></td>
								</tr>
								<tr class="empty-border">
									<td>&nbsp;</td>
									<td class="highlight">CGST <span id="spanCGSTPercent"></span>%:</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>Rs.</td>
									<td><span id="cgstVal"></span></td>
								</tr>
								<tr class="empty-border">
									<td>&nbsp;</td>
									<td class="highlight">SGST <span id="spanSGSTPercent"></span>%:</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>Rs.</td>
									<td><span id="sgstVal"></span></td>
								</tr>
								
								<tr class="empty-border"><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
								
								<tr class="total-row">
									<td class="highlight" colspan="2">
										Grand Total:
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>Rs.</td>
									<td><span id="grandTotal"></span></td>
								</tr>
								<tr  class="empty-border">
									<td colspan="5" style="border-right: none; border-bottom: none" class="space-top-bt">
										<span class="highlight">Amount in words:</span><br> 
										<span id="amount-in-words">&nbsp;</span>
									</td>
								</tr>
							</tfoot>
						</table>
					</td>
				</tr>
				<tr>
					<td class="cust-content space-top-bt" style="border-right: none">
						<span class="highlight">Declaration</span><br>
						<span id="custInfo">
							We declare that this invoice shows the actual prices of the goods<br>
							described and that all particulars are true and correct.
						</span>
					</td>
					<td colspan="2" class="cust-content space-top-bt" align="right" style="border-left: none">
						For <span style="font-weight: bold; text-transform: uppercase;">${CONST_UI_USER_LOGIN_PRINT_NAME}</span><br><br><br>
						Authorized Signatory
					</td>
				</tr>
			</table>
		</div>
	</body>
	<script>
		var APP_PROPS_JSON = ${APP_PROPS};
	</script>
	<script src="assets/js/print.js"></script>
</html>
