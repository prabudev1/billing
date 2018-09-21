
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="layout/head.jsp"></jsp:include>
	</head>
	<body>
		<div class="wrapper">
	        <jsp:include page="layout/menu.jsp">
	        	<jsp:param name="mnActiveHome" value="active" />
	        </jsp:include>
	        
	        <div id="content">
				<jsp:include page="layout/header.jsp">
					<jsp:param name="pageName" value="Dahsboard" />
					
				</jsp:include>
				
				<section class="sec-body">
					<div class="container">
						<div class="row">
							<div class="col-8">
								<div class="card bg-light mb-3">
									<div class="card-header bg-info text-white ">Trending (Recent <span class="spanReportDays"></span> days)</div>
									<div class="card-body">
										<canvas id="line-chart" width="400" height="200"></canvas>	
									</div>
								</div>
							</div>
							<div class="col-4">
								<div class="card-deck">
									<div class="card bg-light mb-3">
										<div class="card-header bg-info text-white ">Revenue (Rs.)</div>
										<div class="card-body">
											<h5 class="card-title"><span id="revenue"></span></h5>
										</div>
									</div>
									<div class="card bg-light mb-3">
										<div class="card-header bg-info text-white ">Customer(s)</div>
										<div class="card-body">
											<h5 class="card-title"><span id="custCount"></span></h5>
										</div>
									</div>
								</div>
								<div class="card-deck">
									<div class="card bg-light mb-3" >
										<div class="card-header bg-info text-white ">Invoice</div>
										<div class="card-body">
											<h5 class="card-title"><span id="invoiceCount"></span></h5>
										</div>
									</div>
									<div class="card bg-light mb-3" >
										<div class="card-header bg-info text-white ">Product(s)</div>
										<div class="card-body">
											<h5 class="card-title"><span id="productCount"></span></h5>
										</div>
									</div>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-12">
								<div class="card bg-light mb-3">
									<div class="card-header bg-info text-white ">Invoice Info (Recent <span class="spanReportDays"></span> days)</div>
									<div class="card-body table-responsive">
										<table class="table table-bordered">
											<thead>
												<tr class="table-active">
													<th scope="col">Bill #</th>
													<th scope="col">Customer Name</th>
													<th scope="col">No.of.Items</th>
													<th scope="col">Total</th>
													<th scope="col">GST</th>
													<th scope="col">Grand Total</th>
													<th scope="col">Generated On</th>
												</tr>
											</thead>
											<tbody id="billingRecords"></tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
				<jsp:include page="layout/footer.jsp"></jsp:include>
	        </div>
    	</div>
	</body>
    <script src="assets/js/chart.js"></script>
    <script src="assets/js/chart.bundle.js"></script>
	<script src="assets/js/home.js"></script>	
</html>
