package spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import spring.domain.member.MemberEntity;
import spring.domain.member.Role;

import java.util.Map;

@Getter
public class AttributeDto {
    // 회원정보 반환 => json 타입 => Map 컬렉션
    // json : 키 , 값 => 한쌍
    private Map<String , Object > attribute; // 회원정보
    private String nameAttributekey; // 요청 정보의 키
    private String name;
    private String email;

    @Builder
    public AttributeDto(Map<String, Object> attribute, String nameAttributekey, String name, String email) {
        this.attribute = attribute;
        this.nameAttributekey = nameAttributekey;
        this.name = name;
        this.email = email;
    }
    // sns 구분 메소드
    public static AttributeDto of(String registrationId , String userNameAttributeName , Map<String , Object> attribute ){

        if( registrationId.equals("naver") ){
            return ofNaver( userNameAttributeName , attribute );
        }
        else if( registrationId.equals("kakao")){
            return ofKakao( userNameAttributeName , attribute );
        }else{
            return ofGoogle( userNameAttributeName , attribute );
        }
    }
    // 구글 회원 가져오기 메소드
    public static AttributeDto ofGoogle(String userNameAttributeName , Map<String , Object> attribute ){

        return AttributeDto.builder()
                .name( (String) attribute.get("name") )
                .email( (String) attribute.get("email") )
                .attribute( attribute )
                .nameAttributekey( userNameAttributeName ).build();
    }
    // 카카오 회원 가져오기 메소드
    public static AttributeDto ofKakao(String userNameAttributeName , Map<String , Object> attribute ){

        Map<String , Object> kakaoAccount = (Map<String, Object>)attribute.get("kakao_account");
        Map<String , Object > profile = (Map<String, Object>) kakaoAccount.get("profile");

        return AttributeDto.builder()
                .name( (String) profile.get("nickname") )
                .email( (String) kakaoAccount.get("email") )
                .attribute( attribute )
                .nameAttributekey( userNameAttributeName ).build();
    }
    // 네이버 회원 가져오기 메소드
    public static AttributeDto ofNaver(String userNameAttributeName , Map<String , Object> attribute ){
        // 네이버의 attribute 이름 : reponse
        Map<String , Object > response = (Map<String, Object>)attribute.get("response");
        return AttributeDto.builder()
                .name( (String) response.get("name") )
                .email( (String) response.get("email") )
                .attribute( attribute )
                .nameAttributekey( userNameAttributeName ).build();
    }


    // dto ----> entity
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .name(name)
                .email(email)
                .role(Role.MEMBER)
                .build();
    }
}
