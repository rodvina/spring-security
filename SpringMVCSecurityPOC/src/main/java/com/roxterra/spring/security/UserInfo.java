package com.roxterra.spring.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Custom principal object
 * @author kahrxo
 *
 */
public class UserInfo extends User {
	
	private static final long serialVersionUID = 1L;
	
	private String customerId;
	private String primaryGat;
	private String secondaryGat;
	private String branchCode;
	private String email;	
	private String firstName;
	private String lastName;
	private String initial;
	private String title;
	private String nickname;
	private String agencyName;

	public UserInfo(String username, String password,
			Collection<? extends GrantedAuthority> authorities, Map<RequestHeaderAttribute, String> userAttributes) {
		super(username, password, authorities);
		extractUserAttributes(userAttributes);		
	}

	public UserInfo(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,  Map<RequestHeaderAttribute, String> userAttributes) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		extractUserAttributes(userAttributes);	
	}
	
	private void extractUserAttributes(Map<RequestHeaderAttribute, String> userAttributes) {
		if (null != userAttributes && !userAttributes.isEmpty()) {
			this.customerId = userAttributes.get(SiteMinderHeaderAttribute.KAH_CUSTOMER_ID);
			this.primaryGat = userAttributes.get(SiteMinderHeaderAttribute.KAH_GAT_CODE);
			this.secondaryGat = userAttributes.get(SiteMinderHeaderAttribute.KAH_GAT_CODE2);
			this.email = userAttributes.get(SiteMinderHeaderAttribute.KAH_EMAIL);
			this.agencyName = userAttributes.get(SiteMinderHeaderAttribute.KAH_AGENCY_NAME);
			this.branchCode = userAttributes.get(SiteMinderHeaderAttribute.KAH_BRANCH);
			this.firstName = userAttributes.get(SiteMinderHeaderAttribute.KAH_FIRST_NAME);
			this.initial = userAttributes.get(SiteMinderHeaderAttribute.KAH_INITIAL);
			this.lastName = userAttributes.get(SiteMinderHeaderAttribute.KAH_LAST_NAME);
			this.nickname = userAttributes.get(SiteMinderHeaderAttribute.KAH_NICKNAME_ID);
			this.title = userAttributes.get(SiteMinderHeaderAttribute.KAH_TITLE);
			
		}
		
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getPrimaryGat() {
		return primaryGat;
	}

	public String getSecondaryGat() {
		return secondaryGat;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getInitial() {
		return initial;
	}

	public String getTitle() {
		return title;
	}

	public String getNickname() {
		return nickname;
	}

	public String getAgencyName() {
		return agencyName;
	}

}
