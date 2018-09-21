		<div class="alert alert-success my-alert" id="cust-success-alert" style="display: none;">
			<button type="button" class="close" data-dismiss="alert">x</button>
			<strong>Success! </strong> Customer have added successfully.
		</div>
		<div class="modal fade hide" id="customerModal">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<h1 class="modal-title">New Customer</h1>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						<form id="customerForm">
							<div class="form-row">
								<div class="form-group col-md-4">
									<label for="inputName">Name</label> 
									<input type="text"class="form-control" id="inputName" placeholder="Name" required="required" maxlength="50" autofocus tabindex="1">
									<div class="invalid-feedback">No, you missed customer name.</div>
								</div>	
								<div class="form-group col-md-2">
									<label for="inputMobile">Mobile</label> 
									<input type="number" class="form-control" id="inputMobile" required="required" placeholder="9999999999" maxlength="12"  tabindex="2">
									<div class="invalid-feedback">No, you missed customer mobile number.</div>
								</div>
								<div class="form-group col-md-3">
									<label for="inputAadhar">AADHAR</label> 
									<input type="text"class="form-control" id="inputAadhar" placeholder="AADHAR No" tabindex="3">
								</div>
								<div class="form-group col-md-3">
									<label for="inputGstin">GSTIN</label>
									<input type="text"class="form-control" id="inputGstin" placeholder="GSTIN No" tabindex="4">
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-4">
									<label for="inputEmail">Email</label> 
									<input type="email"class="form-control" id="inputEmail" placeholder="Email" tabindex="5">
								</div>
								<div class="form-group col-md-2">
									<label for="inputType">Type</label> 
									<select id="inputType" class="form-control">
										<option selected="selected" value="">Choose...</option>
										<option value="I">Individual</option>
										<option value="D">Wholesale Dealer</option>
										<option value="O">Other</option>
									</select>
								</div>
								<div class="form-group col-md-3">
									<label for="inputDob">DOB</label> 
									<input type="text" class="form-control" id="inputDob" name="inputDob" placeholder="DD-MMM-YYYY" tabindex="6">
									<input type="hidden" class="form-control" id="hiddenDob" name="hiddenDob">
								</div>
								<div class="form-group col-md-3">
									<label for="inputGender">Gender</label> 
									<select id="inputGender" class="form-control" tabindex="7">
										<option selected="selected" value="">Choose...</option>
										<option value="M">Male</option>
										<option value="F">Female</option>
										<option value="O">Other</option>
									</select>
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputAddress">Address Line 1</label> 
									<input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St"  maxlength="200" tabindex="8">
								</div>
								<div class="form-group col-md-6">
									<label for="inputAddress2">Address Line 2</label> 
									<input type="text" class="form-control" id="inputAddress2" placeholder="Apartment, studio, or floor" maxlength="200" tabindex="9">
								</div>
							</div>
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputCity">City</label> 
									<input type="text" class="form-control" id="inputCity"  maxlength="50" tabindex="10">
								</div>
								<div class="form-group col-md-4">
									<label for="inputState">State</label> 
									<select id="inputState" class="form-control" tabindex="11">
										<option selected  value="">Choose...</option>
										<option value="Tamil Nadu">Tamil Nadu</option>
									</select>
								</div>
								<div class="form-group col-md-2">
									<label for="inputZip">Zip</label> 
									<input type="text" class="form-control" id="inputZip"  maxlength="8" tabindex="12">
								</div>
							</div>
							<div class="form-group">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" id="isActive" checked="checked" tabindex="13">
									<label class="form-check-label" for="isActive">Active</label>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<input type="hidden" class="form-control" id="hiddenCustId" name="hiddenCustId">
						<button type="button" class="btn btn-primary" id="btnSave" tabindex="14">Save</button>
						<button type="button" class="btn btn-secondary" data-dismiss="modal" tabindex="15">Close</button>
					</div>

				</div>
			</div>
		</div>