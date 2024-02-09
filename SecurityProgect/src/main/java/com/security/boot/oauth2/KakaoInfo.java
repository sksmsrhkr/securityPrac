package com.security.boot.oauth2;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class KakaoInfo implements OAuth2UserInfo{

	private Map<String, Object> attributes;
//    private Map<String, Object> attributesAccount;
//    private Map<String, Object> attributesProfile;
    
//    public KakaoInfo(Map<String, Object> attributes) {
//    	
//    	System.out.print(attributes);
//    	  this.attributes = attributes;
//          this.attributesAccount = (Map<String, Object>) attributes.get("kakao_account");
//          this.attributesProfile = (Map<String, Object>) attributesAccount.get("profile");
//    }
	
	
//	public Boolean setPrivacyInfo;
//	public Long id;
//	public String connected_at;
//	public Properties properties;
//	public KakaoAccount kakao_account;
//	
//		@Data
//		public class Properties {
//		
//		public String nickname;
//		public String profile_image;
//		public String thumbnail_image;
//		}
//		
//		@Data
//		public class KakaoAccount {
//		
//		public Boolean profile_nickname_needs_agreement;
//		public Boolean profile_image_needs_agreement;
//		public Profile profile;
//		public Boolean has_email;
//		public Boolean email_needs_agreement;
//		public Boolean is_email_valid;
//		public Boolean is_email_verified;
//		public String email;
//			
//			@Data
//			public class Profile {
//			
//			public String nickname;
//			public String thumbnail_image_url;
//			public String profile_image_url;
//			public Boolean is_default_image;
//			
//			}
//	
//	}
    

    
		@Override
		public String socialType() {
			return "kakao";
		}
		
		@Override
		public String userEmail() {
			return (String)((Map) attributes.get("kakao_account")).get("email");
		}
		
		@Override
		public String userName() {
			return (String)((Map) attributes.get("properties")).get("nickname");
		}

		@Override
		public String socialId() {	
			return attributes.get("id").toString();
		}

	}









