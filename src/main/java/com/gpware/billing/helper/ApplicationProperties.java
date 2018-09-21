package com.gpware.billing.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

	@Value("${app.config.gst.percent.state}")
	private String gstStatePercent;

	@Value("${app.config.gst.percent.central}")
	private String gstCentralPercent;

	@Value("${app.config.report.days}")
	private Integer reportDays;

	@Value("${app.config.serviceurl.host}")
	private String svcUrl;

	@Value("${app.config.billNo.leadingzeros}")
	private String billNoLeadingZero;

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

	public String getSvcUrl() {
		return svcUrl;
	}

	public void setSvcUrl(String svcUrl) {
		this.svcUrl = svcUrl;
	}

	public String getBillNoLeadingZero() {
		return billNoLeadingZero;
	}

	public void setBillNoLeadingZero(String billNoLeadingZero) {
		this.billNoLeadingZero = billNoLeadingZero;
	}

}
