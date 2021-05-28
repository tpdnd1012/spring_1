package spring.domain.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<PostEntity , Long> {
                                                // 테이블명 , 기본키의 자료형

    // 타이틀 검색               // 엔티티의 클래스명
    @Query(value = "select p from PostEntity p where p.title Like %:search%",
            countQuery = "select count(p.id) from PostEntity p where p.title Like %:search%")
    Page<PostEntity> findAlltitle(String search, Pageable pageable);

    // 쿼리 (value = "select p from 엔티티의클래스명 p where p.필드명 Like %:인수%",
    //      countQuery = select count(p.id) from 엔티티의클래스명 p where p.필드명 Like %:인수%)
        // 조건과, 조건결과의 갯수 => pageable

    // 내용 검색               // 엔티티의 클래스명
    @Query(value = "select p from PostEntity p where p.contents Like %:search%",
            countQuery = "select count(p.id) from PostEntity p where p.contents Like %:search%")
    Page<PostEntity> findAllcontents(String search, Pageable pageable);

    // 작성자 검색               // 엔티티의 클래스명
    @Query(value = "select p from PostEntity p where p.name Like %:search%",
            countQuery = "select count(p.id) from PostEntity p where p.name Like %:search%")
    Page<PostEntity> findAllname(String search, Pageable pageable);

    // 번호 검색               // 엔티티의 클래스명
    @Query(value = "select p from PostEntity p where p.id = :search",
            countQuery = "select count(p.id) from PostEntity p where p.id = :search")
    Page<PostEntity> findAllid(Long search, Pageable pageable);

}
