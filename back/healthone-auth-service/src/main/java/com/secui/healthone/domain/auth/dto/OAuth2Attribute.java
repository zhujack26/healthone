//package com.secui.healthone.domain.auth.dto;
//
//
//import com.secui.healthone.domain.auth.entity.User;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.HashMap;
//import java.util.Map;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.util.Map;
//
//@Getter
//public class OAuth2Attribute {
//    private Map<String, Object> attributes;
//    private String nameAttributeKey;
//    private String name;
//    private String email;
//    private String picture;
//
//    @Builder
//    public OAuth2Attribute(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture){
//        this.attributes = attributes;
//        this.nameAttributeKey = nameAttributeKey;
//        this.name = name;
//        this.email = email;
//        this.picture = picture;
//    }
//
//    public static OAuth2Attribute of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
//        return ofGoogle(userNameAttributeName, attributes);
//    }
//
//    private static OAuth2Attribute ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
//        return OAuth2Attribute.builder()
//                .name((String) attributes.get("name"))
//                .email((String) attributes.get("email"))
//                .picture((String) attributes.get("picture"))
//                .attributes(attributes)
//                .nameAttributeKey(userNameAttributeName)
//                .build();
//    }
//
//    public User toEntity(){
//        return User.builder()
//                .name(name)
//                .email(email)
//                .picture(picture)
////                .role(Role.GUEST)
//                .build();
//    }
//}