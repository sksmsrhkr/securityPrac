package com.security.boot.oauth2;

import java.util.Map;

public interface OAuth2UserInfo {
	
	String socialType();
	String socialId();
	String userEmail();
	String userName();
	
}
