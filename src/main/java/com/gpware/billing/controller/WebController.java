package com.gpware.billing.controller;

import java.security.Principal;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gpware.billing.helper.ApplicationConstants;
import com.gpware.billing.helper.UserProperties;
import com.gpware.billing.helper.UserPropertyLoader;

@Controller
public class WebController {

	private ModelMap setCommonMadalProps(ModelMap model, Principal principal) {
		model.addAttribute(ApplicationConstants.CONST_LOGGED_USER_NAME, principal.getName());
	
		UserProperties userProp = UserPropertyLoader.getUserProperty(principal.getName());
		JSONObject propJson = new JSONObject();
		propJson = new JSONObject();
		try {
			
			model.addAttribute(ApplicationConstants.CONST_UI_USER_LOGIN_DISPLAY_NAME, userProp.getUserDisplayName());
			model.addAttribute(ApplicationConstants.CONST_UI_USER_LOGIN_PRINT_NAME, userProp.getUserPrintName());
			model.addAttribute(ApplicationConstants.CONST_UI_USER_LOGIN_ADDR_HTML, userProp.getAddressHtml());
						
			propJson.put(ApplicationConstants.CONST_GST_STATE_PERCENT, userProp.getGstStatePercent());
			propJson.put(ApplicationConstants.CONST_GST_CENTRAL_PERCENT, userProp.getGstCentralPercent());
			propJson.put(ApplicationConstants.CONST_REPORT_DAYS, userProp.getReportDays());
			propJson.put(ApplicationConstants.CONST_BILL_NO_LEADING_ZERO_LENGTH, userProp.getBillNoLeadingZero());
		} catch (JSONException e) {
		}
		model.addAttribute(ApplicationConstants.APPLICATION_PROPS_KEY, propJson);
		return model;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showIndexPage(ModelMap model, Principal principal) {
		setCommonMadalProps(model, principal);
		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showHomePage(ModelMap model, Principal principal) {
		setCommonMadalProps(model, principal);
		return "home";
	}

	@RequestMapping(value = "/billing", method = RequestMethod.GET)
	public String showBilling(ModelMap model, Principal principal) {
		setCommonMadalProps(model, principal);
		return "billing";
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public String showCustomers(ModelMap model, Principal principal) {
		setCommonMadalProps(model, principal);
		return "customers";
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String showProducts(ModelMap model, Principal principal) {
		setCommonMadalProps(model, principal);
		return "products";
	}

	@RequestMapping(value = "/print", method = RequestMethod.GET)
	public String showPrint(ModelMap model, Principal principal) {
		setCommonMadalProps(model, principal);
		return "print";
	}

	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public String showReports(ModelMap model, Principal principal) {
		setCommonMadalProps(model, principal);
		return "reports";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String showAdmin(ModelMap model, Principal principal) {
		setCommonMadalProps(model, principal);
		return "admin";
	}

}
