<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
	<head>
		<title>GPWARES - Billing</title>
		<meta charset="UTF-8" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name='title' content='Gpware - Info'>
		<meta name='description' content='Simple, efficient and free GST Billing Software. Specially designed for all small business.'>
		<meta name='url' content='http://www.gpwares.com/'>
		<meta name='keywords' content='Billing, GST Software'>
		
		<meta property="og:title" content="GPWARE" />
		<meta property="og:description" content="Simple, efficient and free GST Billing Software. Specially designed for all small business." />
		<meta property="og:url" content="http://www.gpwares.com/" />
		<meta property="og:site_name" content="GPWARE" />
		<meta property="og:type" content="website" />
		<meta property="og:image" content="http://www.gpwares.com/assets/img/logo.png" />
		
		<link rel="Stylesheet" href="assets/css/bootstrap.v4.1.3.css" />
		<link rel="stylesheet" href="assets/css/styles.css" />
		<style>
			table td{
				vertical-align:top;
				border: solid 1px #888;
				padding: 5px;
			}
			.error-header {
				height: 60px;
				margin-bottom: 20px;
				background: #26a69a;
    			border-bottom: 8px solid #4db6ac;
			}
			.err-row {
				padding-bottom: 10px; 
			}
			.err-heading {
				font-weight: bold;
				color: #616161;
			}
			.err-val {
				color: #616161;
			}
		</style>
	</head>
	<body>
		<div class="error-header"></div>
		<div class="container">
			<h1>OOPS! Something went wrong.</h1>
			<div class="text-muted">Please try again after sometime or contact administrator</div><br>
			<div class="form-row">
				<div class="col-md-3 err-row">
					<div class="err-heading">Date:</div>
					<div class="err-val">${timestamp}</div>
				</div>
				<div class="col-md-2 err-row">
					<div class="err-heading">Status:</div>
					<div class="err-val">${status}</div>
				</div>
				<div class="col-md-7 err-row">
					<div class="err-heading">Error:</div>
					<div class="err-val">${error}</div>
				</div>
			</div>
			<div class="err-row">
				<div class="err-heading">Message:</div>
				<div class="err-val">${message == null || message == '' ? 'NA' : message}</div>
			</div>
			<div class="err-row">
				<div class="err-heading">Exception:</div>
				<div class="err-val">${exception == null || exception == '' ? 'NA' : exception}</div>
			</div>
			<div class="err-row">
				<div class="err-heading">Trace:</div>
				<div class="err-val">${trace == null || trace == '' ? 'NA' : trace}</div>
			</div>
	    </div>
	</body>
</html>