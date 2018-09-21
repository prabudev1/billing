		<div class="alert alert-info my-alert" id="cust-prod-exist" style="display: none;">
			<button type="button" class="close" data-dismiss="alert">x</button>
			<strong>Product</strong> item has already exists.
		</div>
		<div class="alert alert-success my-alert" id="prod-add-success" style="display: none;">
			<button type="button" class="close" data-dismiss="alert">x</button>
			<strong>Success! </strong> Product have added successfully.
		</div>
		<div class="modal fade hide" id="productModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title">New Product</h1>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<form id="productForm">
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="formProductName">Name</label> 
									<input type="text"class="form-control" id="formProductName" placeholder="Product Name" required="required" maxlength="100" autofocus tabindex="1">
									<input type="hidden" id="hiddenProductRowId" value="0">
									<div class="invalid-feedback">No, you missed product name.</div>
								</div>
								<div class="form-group col-md-3">
									<label for="inputProductCode">Code</label> 
									<input type="text"class="form-control" id="inputCode" placeholder="Product Code" required="required" maxlength="10" tabindex="2">
									<div class="invalid-feedback">No, you missed product code.</div>
								</div>
								<div class="form-group col-md-3">
									<label for="inputProductValue">Value</label> 
									<input type="number"class="form-control" id="inputProductValue" placeholder="Product Value" required="required"  tabindex="3" step=".01">
									<div class="invalid-feedback">No, you missed product value.</div>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-12">
									<label for="inputProductDesc">Code</label> 
									<input type="text"class="form-control" id="inputProductDesc" placeholder="Product Description" tabindex="4">
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputProductMeasurement">Measurement</label> 
									<input type="text" class="form-control" id="inputProductMeasurement" placeholder="KG" maxlength="5" tabindex="5">
								</div>
								<div class="form-group col-md-6">
									<label for="inputProductType">Type</label> 
									<input type="text" class="form-control" id="inputProductType" placeholder="Beverages" maxlength="25" tabindex="6">
								</div>
							</div>
							<div class="form-group">
								<div class="form-check">
									<input type="hidden" class="form-control" id="hiddenPrdId" name="hiddenPrdId">
									<input class="form-check-input" type="checkbox" id="isProductActive" checked="checked" tabindex="7">
									<label class="form-check-label" for="isProductActive">Active</label>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary" id="btnProductSave" tabindex="8">Save</button>
						<button type="button" class="btn btn-secondary" data-dismiss="modal" tabindex="9">Close</button>
					</div>

				</div>
			</div>
		</div>