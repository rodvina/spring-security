package com.roxterra.spring.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.Assert;

/**
 * This AuthenticationUserDetailsService implementation creates a UserDetails object based solely on the 
 * information contained in the given PreAuthenticatedAuthenticationToken. The user name is set to the 
 * name as returned by PreAuthenticatedAuthenticationToken.getName(), the password is set to a fixed 
 * dummy value (it will not be used by the PreAuthenticatedAuthenticationProvider anyway), the 
 * Granted Authorities are retrieved from the details object as returned by 
 * PreAuthenticatedAuthenticationToken.getDetails().getGrantedAuthorities(), and the User Attributes are
 * retrieved from the details object as returned by PreAuthenticatedAuthenticationToken.getDetails().getUserAttributes().
 *
 * The details object as returned by PreAuthenticatedAuthenticationToken.getDetails() must implement both the 
 * <code>GrantedAuthoritiesContainer</code> interface and the <code>UserAttributeContainer</code> interface
 * for this implementation to work.
 *  
 * @author kahrxo
 *
 */
public class PreAuthenticatedGrantedAuthoritiesUserAttributeUserDetailsService
		implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    /**
     * Get a UserDetails object based on the user name contained in the given
     * token, and the GrantedAuthorities as returned by the
     * GrantedAuthoritiesContainer implementation as returned by
     * the token.getDetails() method.
     */
    public final UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws AuthenticationException {
    	Assert.notNull(token.getDetails());
        Assert.isInstanceOf(GrantedAuthoritiesContainer.class, token.getDetails());
        Assert.isInstanceOf(UserAttributeContainer.class, token.getDetails());
        Map<RequestHeaderAttribute, String> userAttributes = ((UserAttributeContainer)token.getDetails()).getUserAttributes();
        Collection<? extends GrantedAuthority> authorities = ((GrantedAuthoritiesContainer) token.getDetails()).getGrantedAuthorities();
        return createuserDetails(token, authorities, userAttributes);
    }

    /**
     * Creates the final <tt>UserDetails</tt> object. Can be overridden to customize the contents.
     *
     * @param token the authentication request token
     * @param authorities the pre-authenticated authorities.
     */
    protected UserDetails createuserDetails(Authentication token, Collection<? extends GrantedAuthority> authorities,
    		Map<RequestHeaderAttribute, String> userAttributes) {
        return new UserInfo(token.getName(), "N/A", true, true, true, true, authorities, userAttributes);
    }
}
