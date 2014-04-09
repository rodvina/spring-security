package com.roxterra.spring.security;

import java.io.Serializable;
import java.util.Map;

public interface UserAttributeContainer 
	 extends Serializable {
	
	Map<RequestHeaderAttribute, String> getUserAttributes();
}
