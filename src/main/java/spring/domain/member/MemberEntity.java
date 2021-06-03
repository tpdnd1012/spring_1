package spring.domain.member;

// DB 테이블 설계하기
// 1. JPA : 관계형 데이터베이스 JAVA API
// 1. Entity : db 테이블

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.domain.BasTime;
import spring.web.dto.MemberDto;

import javax.persistence.*;

@Getter // 롬북을 이용한 Getter 메소드 자동 생성
@Entity // DB 테이블 관계
@NoArgsConstructor // 기본생성자 제공
public class MemberEntity extends BasTime {

    @Id // javax // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto
    private Long id; // 회원번호

    @Column // 필드
    private String memberid;

    @Column // 필드
    private String password;

    @Column // 필드
    private String name;

    @Column // 필드
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    Role role;

    @Builder
    public MemberEntity(Long id, String memberid, String password, String name, String email, Role role) {
        this.id = id;
        this.memberid = memberid;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // 회원 업데이트 메소드
    public void update(MemberDto updateDto){
        // 인수값을 현재 필드에 넣기
        this.name = updateDto.getName();
        this.email = updateDto.getEmail();
    }

    // Role 키 반환
    public String getkey(){
        return this.role.getKey();
    }

    // oauth2 이메일 업데이트
    public MemberEntity oauthupdate( String email ){
        this.email = email;
        return this;
    }

}
