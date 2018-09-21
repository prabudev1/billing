
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="layout/head.jsp"></jsp:include>
	</head>
	<body>
		<div class="wrapper">
	        <jsp:include page="layout/menu.jsp">
	        	<jsp:param name="mnProductsHome" value="active" />
	        </jsp:include>
	        
	        <div id="content">
				<jsp:include page="layout/header.jsp">
					<jsp:param name="pageName" value="Product List" />
				</jsp:include>
				
				<section class="sec-body">
					<div class="alert alert-success my-alert" id="prod-add-success" style="display: none;">
						<button type="button" class="close" data-dismiss="alert">x</button>
						<strong>Success! </strong> Product have added successfully.
					</div>
					<div class="container">
						<div class="row ">
							<div class="col-4">
								<h3>Search & Filter Info</h3>
							</div>
							<div class="col-6 text-right">
								<button type="button" class="btn btn-primary add-new-product" >Add New Product</button>
								<button type="button" class="btn btn-secondary reload-products" >Reload</button>
							</div>
						</div>
						<div class="row mt-3">
							<div class="col-7 ">
								<div class="form-group" >
									<label for="prdNameMobile" class="prd-label">Name or Code</label> 
									<input type="text" id="prdNameMobile" class="form-control col-sm-5" placeholder="Name or Code">
								</div>
							</div>
							<div class="col-3 text-right">
								<div class="form-group" >
									<label for="prdOrderBy" class="prd-label">Order By</label>
									<select id="prdOrderBy" class="form-control">
										<option value="8" selected="selected">Recently Added First</option>
										<option value="7">Recently Added Last</option>
										
										<option value="1">Ascending By Name</option>
										<option value="2">Descending By Name</option>
										
										<option value="3">Ascending By Code</option>
										<option value="4">Descending By Code</option>
										
										<option value="5">Ascending By Value</option>
										<option value="6">Descending By Value</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-10">
								<div id="prd-managment-grid" class="jsgrid"></div>
							</div>
						</div>
						<div class="row mt-5">
							<div class="col-10">
								<button type="button" class="btn btn-primary add-new-product">Add New Product</button>
								<button type="button" class="btn btn-secondary reload-products" >Reload</button>
							</div>
						</div>
					</div>
				</section>
				<jsp:include page="layout/modal-product.jsp"></jsp:include>
				<jsp:include page="layout/footer.jsp"></jsp:include>
	        </div>
    	</div>
	</body>
    <script src="assets/js/jsgrid.js"></script>
	<script src="assets/js/products.js"></script>
</html>
