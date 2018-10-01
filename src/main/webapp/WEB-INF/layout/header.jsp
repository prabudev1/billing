
<section class="sec-header">
	<div class="container">
		<div class="row">
			<div class="col-8">
				<h1 class="page-heading">${param.pageName} </h1>
			</div>
			<div class="col-4">
				<div class="user-icon pull-right">
					<span class="fa fa-user"></span>
					<span class="user-icon-name">${LOGGED_USER_NAME}</span>
					<input type="hidden" id="loggedUserId" name="loggedUserId" value="${LOGGED_USER_NAME}">
				</div>
			</div>
		</div>
	</div>
</section>

<script>
	var APP_PROPS_JSON = ${APP_PROPS};
</script>
