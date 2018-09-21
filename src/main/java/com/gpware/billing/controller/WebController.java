package com.gpware.billing.controller;

import javax.annotation.PostConstruct;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gpware.billing.helper.ApplicationConstants;
import com.gpware.billing.helper.ApplicationProperties;

@Controller
public class WebController {

	@Autowired
	private ApplicationProperties appProp;
	
	private JSONObject propJson = new JSONObject();
	
	@PostConstruct
	public void init() {
		propJson = new JSONObject();
		try {
			propJson.put(ApplicationConstants.CONST_GST_STATE_PERCENT, appProp.getGstStatePercent());
			propJson.put(ApplicationConstants.CONST_GST_CENTRAL_PERCENT, appProp.getGstCentralPercent());
			propJson.put(ApplicationConstants.CONST_REPORT_DAYS, appProp.getReportDays());
			propJson.put(ApplicationConstants.CONST_BILL_NO_LEADING_ZERO_LENGTH, appProp.getBillNoLeadingZero());
			
		} catch (JSONException e) {
		}
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showHomePage(ModelMap model) {
		model.addAttribute(ApplicationConstants.APPLICATION_PROPS_KEY, propJson);
		return "home";
	}

	@RequestMapping(value = "/billing", method = RequestMethod.GET)
	public String showBilling(ModelMap model) {
		model.addAttribute(ApplicationConstants.APPLICATION_PROPS_KEY, propJson);
		return "billing";
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public String showCustomers(ModelMap model) {
		model.addAttribute(ApplicationConstants.APPLICATION_PROPS_KEY, propJson);
		return "customers";
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String showProducts(ModelMap model) {
		model.addAttribute(ApplicationConstants.APPLICATION_PROPS_KEY, propJson);
		return "products";
	}
	
	@RequestMapping(value = "/print", method = RequestMethod.GET)
	public String showPrint(ModelMap model) {
		model.addAttribute(ApplicationConstants.APPLICATION_PROPS_KEY, propJson);
		return "print";
	}
	
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public String showReports(ModelMap model) {
		model.addAttribute(ApplicationConstants.APPLICATION_PROPS_KEY, propJson);
		return "reports";
	}

}
