package spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.domain.post.PostEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor // 빈생성자
@Setter
public class PostDto {

    private Long id; // 게시물 번호 pk , auto
    private String title; // 게시물 제목
    private String contents; // 게시물 내용
    private String name; // 게시물 작성자
    private int count; // 게시물 조회수
    private LocalDateTime createDate; // 게시물 작성일

    @Builder
    public PostDto(Long id, String title, String contents, String name, int count, LocalDateTime createDate) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.count = count;
        this.createDate = createDate;
    }

    // Dto ----> entitey 메소드
    public PostEntity toEntity() {
        return PostEntity.builder()
                .title(title)
                .contents(contents)
                .name(name)
                .count(count).build();
    }
}
