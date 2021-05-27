package spring.domain.card;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
                                // 상속 : JpaRepository< 엔티티명 , 엔티티 기본키의 자료형 >
}
