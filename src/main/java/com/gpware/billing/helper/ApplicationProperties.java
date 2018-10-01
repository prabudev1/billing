package com.gpware.billing.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

	@Value("${app.config.user.admin.name}")
	private String adminUserName;

	@Value("${app.config.user.admin.pwd}")
	private String adminUserPwd;

	@Value("${app.config.user.names}")
	private String userNames;

	@Value("${app.config.user.delimiter}")
	private String userPwdDelimiter;

	@Value("${app.config.user.props.file.path}")
	private String userPropsFilePath;

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getAdminUserPwd() {
		return adminUserPwd;
	}

	public void setAdminUserPwd(String adminUserPwd) {
		this.adminUserPwd = adminUserPwd;
	}

	public String getUserNames() {
		return userNames;
	}

	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	public String getUserPwdDelimiter() {
		return userPwdDelimiter;
	}

	public void setUserPwdDelimiter(String userPwdDelimiter) {
		this.userPwdDelimiter = userPwdDelimiter;
	}

	public String getUserPropsFilePath() {
		return userPropsFilePath;
	}

	public void setUserPropsFilePath(String userPropsFilePath) {
		this.userPropsFilePath = userPropsFilePath;
	}

}
