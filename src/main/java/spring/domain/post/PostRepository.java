package spring.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<PostEntity , Long> {
                                                // 테이블명 , 기본키의 자료형

    @Query(value = "select p from post p where p.?1 = %:?2%", nativeQuery = true)
    Page<PostEntity> findAllsearch(String keyword, String search, Pageable pageable);

}
