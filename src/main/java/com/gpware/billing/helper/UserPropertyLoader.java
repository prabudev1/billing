package com.gpware.billing.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;

public class UserPropertyLoader {

	private static Map<String, UserProperties> userPropertyMap = new HashMap<String, UserProperties>();

	private UserPropertyLoader() {

	}

	public static Map<String, String> loadUserPropertyMap(String propFilePath, String[] userNameArray) {
		Map<String, String> userNamePwdMap = new HashMap<String, String>();
		
		userPropertyMap = new HashMap<String, UserProperties>();
		String localUserName;
		for (String userName : userNameArray) {
			localUserName = userName.toLowerCase();
			UserProperties userProp = getUserPropertyFromFile(propFilePath, localUserName);
			userNamePwdMap.put(userProp.getUserLoginName(), userProp.getUserLoginPwd());
			userPropertyMap.put(localUserName, userProp);
		}
		return userNamePwdMap;
	}

	public static UserProperties getUserProperty(String userName) {
		UserProperties userProp = null;
		if (userName != null && !userName.equalsIgnoreCase("")) {
			String localUserName;
			localUserName = userName.toLowerCase();
			userProp = userPropertyMap.get(localUserName);
		}  else {
			
		}
		if (userProp == null) {
			userProp = userPropertyMap.get(ApplicationConstants.CONST_USER_LOGIN_DEFAULT_NAME);
		}
		return userProp;
	}
	
	private static UserProperties getUserPropertyFromFile(String propFilePath, String userName) {
		UserProperties userProp = new UserProperties();
		if (userName != null && !userName.equalsIgnoreCase("")) {
			Properties properties = new Properties();
			try {
				File file = new File(propFilePath + "application.user." +  userName + ".properties");
				InputStream in = new FileInputStream(file);
				properties.load(in);
				
				userProp.setUserLoginName(properties.getProperty(ApplicationConstants.CONST_USER_LOGIN_NAME));
				userProp.setUserLoginPwd(properties.getProperty(ApplicationConstants.CONST_USER_LOGIN_PWD));
				userProp.setUserDisplayName(properties.getProperty(ApplicationConstants.CONST_USER_LOGIN_DISPLAY_NAME));
				userProp.setUserPrintName(properties.getProperty(ApplicationConstants.CONST_USER_LOGIN_PRINT_NAME));
				userProp.setAddressHtml(properties.getProperty(ApplicationConstants.CONST_USER_LOGIN_ADDR_HTML));
				userProp.setGstCentralPercent(properties.getProperty(ApplicationConstants.CONST_USER_GST_CENTRAL_PERCENT));
				userProp.setGstStatePercent(properties.getProperty(ApplicationConstants.CONST_USER_GST_STATE_PERCENT));
				userProp.setReportDays(Integer.parseInt(properties.getProperty(ApplicationConstants.CONST_USER_REPORT_DAYS, "0")));
				userProp.setBillNoLeadingZero(properties.getProperty(ApplicationConstants.CONST_USER_BILL_NO_LEADING_ZERO_LENGTH));
			} catch (IOException e) {
				userProp = null;
			}
		}
		return userProp;
	}
}
