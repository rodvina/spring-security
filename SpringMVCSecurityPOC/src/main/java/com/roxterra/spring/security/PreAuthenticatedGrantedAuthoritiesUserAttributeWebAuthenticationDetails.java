package com.roxterra.spring.security;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails;

/**
 * This WebAuthenticationDetails implementation allows for storing a list of
 * pre-authenticated Granted Authorities and User Attributes. 
 */
public class PreAuthenticatedGrantedAuthoritiesUserAttributeWebAuthenticationDetails
		extends PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails implements UserAttributeContainer {

	private static final long serialVersionUID = 1L;
	private Map<RequestHeaderAttribute, String> userAttributes;
	
	public PreAuthenticatedGrantedAuthoritiesUserAttributeWebAuthenticationDetails(
			HttpServletRequest request,
			Collection<? extends GrantedAuthority> authorities) {
		super(request, authorities);
	}

	public PreAuthenticatedGrantedAuthoritiesUserAttributeWebAuthenticationDetails(
			HttpServletRequest request,
			Collection<? extends GrantedAuthority> authorities,
			Map<RequestHeaderAttribute, String> userAttributes) {
		super(request, authorities);
		this.userAttributes = userAttributes;
	}

	@Override
	public Map<RequestHeaderAttribute, String> getUserAttributes() {
		return userAttributes;
	}

	public void setUserAttributes(Map<RequestHeaderAttribute, String> userAttributes) {
		this.userAttributes = userAttributes;
	}

}
