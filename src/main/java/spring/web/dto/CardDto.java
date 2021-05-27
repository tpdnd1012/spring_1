package spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.domain.card.CardEntity;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자
public class CardDto {

    // 필드
    private Long id;
    private String cardname;
    private String cardcompany;
    private String cardlink;
    private String cardpicture;
    private int cardcount;

    // 생성자
    @Builder
    public CardDto(Long id, String cardname, String cardcompany, String cardlink, String cardpicture, int cardcount) {
        this.id = id;
        this.cardname = cardname;
        this.cardcompany = cardcompany;
        this.cardlink = cardlink;
        this.cardpicture = cardpicture;
        this.cardcount = cardcount;
    }

    // DTO 모든필드 ---> Entity 로 이동
    public CardEntity toEntity() {

        return CardEntity.builder()
                .id(id)
                .cardname(cardname)
                .cardcompany(cardcompany)
                .cardlink(cardlink)
                .cardpicture(cardpicture)
                .cardcount(cardcount).build();

    }

}
