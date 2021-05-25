package spring.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity , Long> {
                                                // 테이블명 , 기본키의 자료형
}
