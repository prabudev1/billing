
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
				
				<section class="sec-body-plain">
					<div class="container">
						<div class="row">
							<div class="col-12">
								<div class="card-deck card-numbers">
									<div class="card mb-3 mt-3">
										<div class="card-body">
											<p class="hm-card-count-title">Revenue</p>
											<p class="hm-card-count-value">Rs. &nbsp;<span id="revenue"></span></p>
										</div>
									</div>
									<div class="card mb-3 mt-3">
										<div class="card-body">
											<p class="hm-card-count-title">Customer</p>
											<p class="hm-card-count-value"><span id="custCount"></span></p>
										</div>
									</div>
									<div class="card mb-3 mt-3">
										<div class="card-body">
											<p class="hm-card-count-title">Invoice</p>
											<p class="hm-card-count-value"><span id="invoiceCount"></span></p>
										</div>
									</div>
									<div class="card mb-3 mt-3">
										<div class="card-body">
											<p class="hm-card-count-title">Product</p>
											<p class="hm-card-count-value"><span id="productCount"></span></p>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-6 col-chart" >
								<div class="card bg-light mb-3">
									<div class="card-body">
										<div class="card-header">
											Billing Trending 
											<p class="text-muted">Recent <span class="spanReportDays"></span> days</p>
										</div>
										<canvas id="line-chart" width="400" height="200"></canvas>	
									</div>
								</div>
							</div>
							<div class="col-6 col-chart">
								<div class="card bg-light mb-3">
									<div class="card-body">
										<div class="card-header">
											Product Trending 
											<p class="text-muted">Recent <span class="spanReportDays"></span> days</p>
										</div>
										<canvas id="doughnut-chart" width="400" height="200"></canvas>	
									</div>
								</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-12">
								<div class="card bg-light mb-3">
									<div class="card-body table-responsive">
										<div class="card-header">
											Invoice List 
											<p class="text-muted">Recent <span class="spanReportDays"></span> days</p>
										</div>
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
