package spring.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
                                // 상속 : JpaRepository< 엔티티명 , 엔티티 기본키의 자료형 >

}
