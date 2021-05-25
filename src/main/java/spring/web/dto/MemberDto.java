package spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import spring.domain.member.MemberEntity;

@Getter // 롬복 어노테이션
@Setter
public class MemberDto {
    // 필드
    private Long id ;
    private String memberid;
    private String password;
    private String name;
    private String email;

    // 생성자
    @Builder  // 롬복 어노테이션
    public MemberDto(Long id, String memberid, String password, String name, String email) {
        this.id = id;
        this.memberid = memberid;
        this.password = password;
        this.name = name;
        this.email = email;
    }
    // DTO 모든필드  ---->  Entity 로 이동
    public MemberEntity toEntity(){
        // builder 없을경우
            // return new MemberEntity( id , memberid , password , name , email );
        return MemberEntity.builder()
                .id(id)
                .memberid(memberid)
                .name(name)
                .password(password)
                .email(email).build();
    }
}
