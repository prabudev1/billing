
<nav id="sidebar">
	<div class="sidebar-header head-title-3">${CONST_UI_USER_LOGIN_DISPLAY_NAME}</div>
	<ul class="list-unstyled components">
       	<li class="${param.mnActiveHome}"><a href="/home"><span class='fa fa-home'></span>Home</a></li>
		<li class="${param.mnBillingHome}"><a href="/billing"><span class='fa fa-book'></span>Billing</a></li>
		<li class="${param.mnProductsHome}"><a href="/products"><span class='fa fa-star'></span>Products</a></li>
		<li class="${param.mnCustomersHome}"><a href="/customers"><span class='fa fa-users'></span>Customers</a></li>
		<li class="${param.mnReports}"><a href="/reports"><span class='fa fa-dashboard'></span>Reports</a></li>
      </ul>
</nav>

<nav class="navbar navbar-dark bg-menu" id="topbar">
	
	<button class="navbar-toggler collapsed" type="button"
		data-toggle="collapse" data-target="#navbarsExample01"
		aria-controls="navbarsExample01" aria-expanded="true"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	
	
	<div class="sidebar-header head-title-3">Kavin Snacks</div>
	
	<div class="m-user-icon">
		<span class="fa fa-user"></span>
	</div>

	<div class="navbar-collapse collapse" id="navbarsExample01" style="">
		<ul class="navbar-nav mr-auto">
	      	<li class="nav-item ${param.mnActiveHome}"><a class="nav-link" href="/home"><span class='fa fa-home'></span>Home</a></li>
			<li class="nav-item ${param.mnBillingHome}"><a class="nav-link" href="/billing"><span class='fa fa-book'></span>Billing</a></li>
			<li class="nav-item ${param.mnProductsHome}"><a class="nav-link" href="/products"><span class='fa fa-star'></span>Products</a></li>
			<li class="nav-item ${param.mnCustomersHome}"><a class="nav-link" href="/customers"><span class='fa fa-users'></span>Customers</a></li>
			<li class="nav-item ${param.mnReports}"><a class="nav-link" href="/reports"><span class='fa fa-dashboard'></span>Reports</a></li>
		</ul>
	</div>
</nav>

<div class="loader-container" id="pageLoadingContainer">
	<div class="lds-roller"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div>
</div>

