package spring.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 상속 : JpaRepository< 엔티티명 , 엔티티 기본키의 자료형 >

    // jpa 메소드 만들기
    // 반환타입 : Optional
    // 메소드 이름 : findByemail
    // 저장되는 엔티티 :  MemmberEntity
    // 찾는 값 : String email
    Optional<MemberEntity> findByemail(String email);

}
