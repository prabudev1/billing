
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="layout/head.jsp"></jsp:include>
	</head>
	<body>
		<div class="wrapper">
	        <jsp:include page="layout/menu.jsp">
					<jsp:param name="mnReports" value="active" />
			</jsp:include>
	        
	        <div id="content">
				<jsp:include page="layout/header.jsp">
					<jsp:param name="pageName" value="Billing Reports" />
				</jsp:include>
				<section class="sec-body">
					<div class="container">
						<div class="row ">
							<div class="col-7">
								<h3>Search & Filter Info</h3>
							</div>
							<div class="col-5">
								<div class="pull-right report-icon report-icon-top">
									<span class="fa fa-file-excel-o" id="rpt-excel"></span>
									<span class="fa fa-refresh" id="rpt-reload"></span>
									<span class="fa fa-bars" id="rpt-reload" data-toggle="collapse" data-target="#reportFilterMenu"
									aria-controls="reportFilterMenu" aria-expanded="true"
									aria-label="Toggle navigation"></span>
								</div>
							</div>
						</div>
						<div class="form-row mt-3 collapse show" id="reportFilterMenu">
							<div class="col-md-4 ">
								<div class="form-group" >
									<label for="custNameMobile" class="cust-label">Bill Date Range</label> 
									<div id="from-to-date-picker" class="form-control">
                                    	<img src="assets/img/calendar.png" class="icon-img"/>
                                    	<span></span> <i class="fa fa-caret-down"></i>
                                    	
                                	</div>
                                	<input type="hidden" class="form-control" id="hidden-date-from" name="hidden-date-from">
                                	<input type="hidden" class="form-control" id="hidden-date-to" name="hidden-date-to">
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group" >
									<label for="listView" class="cust-label">View</label>
									<select id="listView" class="form-control">
										<option value="1" selected="selected">List View</option>
										<option value="2">Tabular View</option>
									</select>
								</div>
							</div>
							<div class="col-md-3">
								<div class="form-group" >
									<label for="billOrderBy" class="cust-label">Order By</label>
									<select id="billOrderBy" class="form-control">
										<option value="1" selected="selected">Recently Generated First</option>
										<option value="2">Recently Generated Last</option>
										<option value="5">Ascending By Grand Total</option>
										<option value="6">Descending By Grand Total</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-12">
								<div id="cust-managment-grid" class="jsgrid"></div>
							</div>
						</div>
					</div>
				</section>
				<jsp:include page="layout/footer.jsp"></jsp:include>
	        </div>
    	</div>
	</body>
    <script src="assets/js/jsgrid.js"></script>
	<script src="assets/js/reports.js"></script>
</html>
