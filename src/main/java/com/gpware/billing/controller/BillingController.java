package com.gpware.billing.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.gpware.billing.dto.BillingDTO;
import com.gpware.billing.dto.BillingReportDTO;
import com.gpware.billing.helper.BillingHelper;
import com.gpware.billing.helper.UserProperties;
import com.gpware.billing.helper.UserPropertyLoader;
import com.gpware.billing.model.Billing;
import com.gpware.billing.services.IBillingService;

@Controller
@RequestMapping("billing")
@CrossOrigin
public class BillingController {
	@Autowired
	private IBillingService billingService;

	private BillingHelper billingHelper = new BillingHelper();

	@GetMapping("get/{id}")
	public ResponseEntity<BillingDTO> getBillingById(@PathVariable("id") Integer id) {
		Billing billing = billingService.getBillingById(id);
		BillingDTO billingDto = billingHelper.copyBilling(billing);
		return new ResponseEntity<BillingDTO>(billingDto, HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping("getBillingList")
	public ResponseEntity<List<BillingDTO>> getBillingList(Principal principal, @RequestParam("fd") String fromDate, @RequestParam("td") String toDate, @RequestParam("o") String orderBy) {
		List<Billing> billingList = billingService.getBillingList(principal.getName(), fromDate, toDate, orderBy);
		List<BillingDTO> billingDtoList = billingHelper.copyBillingList(billingList);
		return new ResponseEntity<List<BillingDTO>>(billingDtoList, HttpStatus.OK);
	}

	@CrossOrigin
	@PostMapping("add")
	public ResponseEntity<String> addBilling(Principal principal, @RequestBody BillingDTO billingDto) {
		Billing billing = billingHelper.copyBilling(billingDto);
		billing.setCreatedBy(principal.getName());
		billingService.addBilling(billing);
		return new ResponseEntity<String>(billing.getId() + "", HttpStatus.OK);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<Void> deleteBilling(@PathVariable("id") Integer id) {
		billingService.deleteBilling(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping("getReportData")
	public ResponseEntity<BillingReportDTO> getReportData(Principal principal) {
		UserProperties userProp = UserPropertyLoader.getUserProperty(principal.getName());
		BillingReportDTO billReport = billingService.getReportData(principal.getName(), userProp.getReportDays());
		return new ResponseEntity<BillingReportDTO>(billReport, HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("downloadExcel")
	public HttpEntity<byte[]> downloadExcelReport(Principal principal, @RequestParam("fd") String fromDate, @RequestParam("td") String toDate, @RequestParam("o") String orderBy) throws IOException {
		byte[] excelContent = billingService.downloadExcel(principal.getName(), fromDate, toDate, orderBy);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=my_file.xlsx");
		header.setContentLength(excelContent.length);
		return new HttpEntity<byte[]>(excelContent, header);
	}
}