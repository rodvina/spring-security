package com.roxterra.spring.security;


public enum SiteMinderHeaderAttribute implements RequestHeaderAttribute {
	KAH_GAT_CODE,
	KAH_GAT_CODE2,
	KAH_ROLE,
	KAH_EMAIL,
	KAH_FIRST_NAME,
	KAH_INITIAL,
	KAH_LAST_NAME,
	KAH_TITLE,
	KAH_NICKNAME_ID,
	KAH_CUSTOMER_ID,
	KAH_BRANCH,
	KAH_AGENCY_NAME;

	@Override
	public RequestHeaderAttribute[] getValues() {
		return SiteMinderHeaderAttribute.values();
	}
}
