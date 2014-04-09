package com.roxterra.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.Attributes2GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAttributes2GrantedAuthoritiesMapper;
import org.springframework.util.Assert;

/**
 * Implementation of <code>AuthenticationDetailsSource</code> which converts the user's request header
 * roles into <code>GrantedAuthority</code>s and stores these in the authentication details object, 
 * <code>PreAuthenticatedGrantedAuthoritiesUserAttributeWebAuthenticationDetails</code>.  In addition,
 * request header user attributes are stored in a map within the same authentication details object.  The
 * various request header user attributes are defined by the header keys, which are encapsulated within
 * a custom enum implementation of <code>RequestHeaderAttribute</code>.
 * <p>
 * The <code>PreAuthenticatedGrantedAuthoritiesUserAttributeWebAuthenticationDetails</code> is configured
 * with a <code>GrantedAuthoritiesContainer</code> and a <code>UserAttributeContainer</code>
 * 
 * @author kahrxo
 *
 */
public class RequestHeaderBasedPreAuthenticatedWebAuthenticationDetailsSource
				implements AuthenticationDetailsSource<HttpServletRequest, PreAuthenticatedGrantedAuthoritiesUserAttributeWebAuthenticationDetails>,
				InitializingBean {

    protected final Log logger = LogFactory.getLog(getClass());

    protected Attributes2GrantedAuthoritiesMapper requestHeaderUserRoles2GrantedAuthoritiesMapper =
        new SimpleAttributes2GrantedAuthoritiesMapper();
    
    public RequestHeaderAttribute requestHeaderAttribute;
    
	@Override
	public void afterPropertiesSet() throws Exception {
        Assert.notNull(requestHeaderUserRoles2GrantedAuthoritiesMapper, "Roles to granted authorities mapper not set");
	}

	@Override
	public PreAuthenticatedGrantedAuthoritiesUserAttributeWebAuthenticationDetails buildDetails(
			HttpServletRequest context) {
        Collection<String> userRoles = getUserRoles(context);
        Collection<? extends GrantedAuthority> userGas = requestHeaderUserRoles2GrantedAuthoritiesMapper.getGrantedAuthorities(userRoles);

        if (logger.isDebugEnabled()) {
            logger.debug("Request header roles [" + userRoles + "] mapped to Granted Authorities: [" + userGas + "]");
        }

        Map<RequestHeaderAttribute, String> userAttributes = getUserAttributes(context);
        
        PreAuthenticatedGrantedAuthoritiesUserAttributeWebAuthenticationDetails result =
                new PreAuthenticatedGrantedAuthoritiesUserAttributeWebAuthenticationDetails(context, userGas, userAttributes);
        
        return result;
	}
	
	/**
	 * Return map of userAttributes derived from the request headers
	 * @param context
	 * @return
	 */
    private Map<RequestHeaderAttribute, String> getUserAttributes(HttpServletRequest context) {
    	Map<RequestHeaderAttribute, String> userAttributes = new HashMap<RequestHeaderAttribute, String>();
    	for (RequestHeaderAttribute headerAttribute : requestHeaderAttribute.getValues()) {
    		String headerValue = context.getHeader(headerAttribute.toString());
    		userAttributes.put(headerAttribute, headerValue);
    	}
		return userAttributes;
	}

    
	private Collection<String> getUserRoles(HttpServletRequest request) {
        ArrayList<String> userRolesList = new ArrayList<String>();

        String userRoles = request.getHeader(SiteMinderHeaderAttribute.KAH_ROLE.toString());
        String[] groupArray = userRoles.split("\\^");
		for (int i = 0; i < groupArray.length; i++) {
			userRolesList.add(groupArray[i]);
		}

        return userRolesList;
    }

	public RequestHeaderAttribute getRequestHeaderAttribute() {
		return requestHeaderAttribute;
	}

	public void setRequestHeaderAttribute(
			RequestHeaderAttribute requestHeaderAttribute) {
		this.requestHeaderAttribute = requestHeaderAttribute;
	}

}
