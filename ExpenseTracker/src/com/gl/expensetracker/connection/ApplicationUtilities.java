package com.gl.expensetracker.connection;

import java.util.Properties;

public class ApplicationUtilities {

	public static Properties EXPENSE_TRACKER_PROPERTIES = new Properties();
	public static String getCustomProperty(String propertyKey,
			String defaultValue) {
		if (null == defaultValue || "".equals(defaultValue)) {
			return EXPENSE_TRACKER_PROPERTIES.getProperty(propertyKey);
		} else {
			return EXPENSE_TRACKER_PROPERTIES
					.getProperty(propertyKey, defaultValue);
		}
	}

}
