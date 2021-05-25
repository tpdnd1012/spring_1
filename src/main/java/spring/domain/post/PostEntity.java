package spring.domain.post;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.domain.BasTime;
import spring.web.dto.PostDto;

import javax.persistence.*;

@Entity // 해당 클래스는 db 테이블
@Getter
@NoArgsConstructor // 빈생성자
public class PostEntity extends BasTime {
                            // db 생성시간, 수정시간 클래스 상속
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY ) // 오토키 => 자동번호
    private Long id; // 게시물 번호 pk , auto

    @Column
    private String title; // 게시물 제목

    @Column
    private String contents; // 게시물 내용

    @Column
    private String name; // 게시물 작성자

    @Column
    private int count; // 게시물 조회수

    @Builder
    public PostEntity(Long id, String title, String contents, String name, int count) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.count = count;
    }
    // 업데이트 메소드
    public void update(PostDto postDto ){
        this.title = postDto.getTitle();
        this.contents = postDto.getContents();
    }
    // 조회수 증가 메소드
    public void countup(){
        this.count++ ;
    }
}
