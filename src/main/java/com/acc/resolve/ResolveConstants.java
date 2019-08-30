package com.acc.resolve;

import java.util.HashMap;
import java.util.Map;

public class ResolveConstants {

	@SuppressWarnings("serial")
	public static final Map<String, String> CUSTOMER_FIELD = new HashMap<String, String>(){{
		put("1", "fullName");// 客户全称
	}};
	
	public static String getCustomerFieldName (String key) {
		return CUSTOMER_FIELD.get(key);
	}
	
	@SuppressWarnings("serial")
	public static final Map<String, String> CONTACT_FIELD = new HashMap<String, String>(){{
		put("1", "customerId");// 客户全称
	}};
	
	public static String getContactFieldName (String key) {
		return CONTACT_FIELD.get(key);
	}
	
}
