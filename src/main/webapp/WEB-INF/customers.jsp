
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="layout/head.jsp"></jsp:include>
	</head>
	<body>
		<div class="wrapper">
	        <jsp:include page="layout/menu.jsp">
					<jsp:param name="mnCustomersHome" value="active" />
			</jsp:include>
	        
	        <div id="content">
				<jsp:include page="layout/header.jsp">
					<jsp:param name="pageName" value="Customer List" />
				</jsp:include>
				
				<section class="sec-body">
					<div class="alert alert-success my-alert" id="cust-success-alert" style="display: none;">
						<button type="button" class="close" data-dismiss="alert">x</button>
						<strong>Success! </strong> Customer have added successfully.
					</div>
					<div class="container">
						<div class="row ">
							<div class="col-4">
								<h3>Search & Filter Info</h3>
							</div>
							<div class="col-6 text-right">
								<button type="button" class="btn btn-primary add-new-customer" >Add New Customer</button>
								<button type="button" class="btn btn-secondary reload-customers" >Reload</button>
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-7 ">
								<div class="form-group" >
									<label for="custNameMobile" class="cust-label">Name or Mobile</label> 
									<input type="text" id="custNameMobile" class="form-control col-sm-5" placeholder="Name or Mobile">
								</div>
							</div>
							<div class="col-3 text-right">
								<div class="form-group" >
									<label for="custOrderBy" class="cust-label">Order By</label>
									<select id="custOrderBy" class="form-control">
										<option value="6" selected="selected">Recently Added First</option>
										<option value="3">Recently Added Last</option>
										<option value="1">Ascending By Name</option>
										<option value="4">Descending By Name</option>
										<option value="2">Ascending By Email</option>
										<option value="5">Descending By Email</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-10">
								<div id="cust-managment-grid" class="jsgrid"></div>
							</div>
						</div>
						<div class="row mt-5">
							<div class="col-10">
								<button type="button" class="btn btn-primary add-new-customer">Add New Customer</button>
								<button type="button" class="btn btn-secondary reload-customers" >Reload</button>
							</div>
						</div>
					</div>
				</section>
				<jsp:include page="layout/modal-customer.jsp"></jsp:include>
				<jsp:include page="layout/footer.jsp"></jsp:include>
	        </div>
    	</div>
	</body>
    <script src="assets/js/jsgrid.js"></script>
	<script src="assets/js/customer.js"></script>
</html>
