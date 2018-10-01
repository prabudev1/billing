package com.gpware.billing.helper;

public class UserProperties {

	private String userLoginName;
	private String userLoginPwd;
	private String userDisplayName;
	private String userPrintName;
	private String addressHtml;
	private String gstStatePercent;
	private String gstCentralPercent;
	private Integer reportDays;
	private String billNoLeadingZero;

	public String getUserLoginName() {
		return userLoginName;
	}

	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}

	public String getUserLoginPwd() {
		return userLoginPwd;
	}

	public void setUserLoginPwd(String userLoginPwd) {
		this.userLoginPwd = userLoginPwd;
	}

	public String getUserDisplayName() {
		return userDisplayName;
	}

	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}

	public String getUserPrintName() {
		return userPrintName;
	}

	public void setUserPrintName(String userPrintName) {
		this.userPrintName = userPrintName;
	}

	public String getAddressHtml() {
		return addressHtml;
	}

	public void setAddressHtml(String addressHtml) {
		this.addressHtml = addressHtml;
	}

	public String getGstStatePercent() {
		return gstStatePercent;
	}

	public void setGstStatePercent(String gstStatePercent) {
		this.gstStatePercent = gstStatePercent;
	}

	public String getGstCentralPercent() {
		return gstCentralPercent;
	}

	public void setGstCentralPercent(String gstCentralPercent) {
		this.gstCentralPercent = gstCentralPercent;
	}

	public Integer getReportDays() {
		return reportDays;
	}

	public void setReportDays(Integer reportDays) {
		this.reportDays = reportDays;
	}

	public String getBillNoLeadingZero() {
		return billNoLeadingZero;
	}

	public void setBillNoLeadingZero(String billNoLeadingZero) {
		this.billNoLeadingZero = billNoLeadingZero;
	}

}
