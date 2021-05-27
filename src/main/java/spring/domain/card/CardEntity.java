package spring.domain.card;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.domain.BasTime;
import spring.web.dto.CardDto;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor // 기본 생성자
public class CardEntity extends BasTime {

    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO
    private Long id; // 카드번호

    @Column
    private String cardname; // 카드명

    @Column
    private String cardcompany; // 카드사

    @Column
    private String cardlink; // 링크

    @Column
    private String cardpicture; // 이미지

    @Column
    private int cardcount; // 조회수

    @Builder // 1. 인수 순서 구분x 2. 인수 null 제어   [ 객체 생성하는데 안전성 보장 ]
    public CardEntity(Long id, String cardname, String cardcompany, String cardlink, String cardpicture, int cardcount) {
        this.id = id;
        this.cardname = cardname;
        this.cardcompany = cardcompany;
        this.cardlink = cardlink;
        this.cardpicture = cardpicture;
        this.cardcount = cardcount;
    }
    
    // 업데이트 메소드
    public void Cardupdate(CardDto cardDto) {

        this.cardname = cardDto.getCardname();
        this.cardcompany = cardDto.getCardcompany();
        this.cardlink = cardDto.getCardlink();
        this.cardpicture = cardDto.getCardpicture();

    }
    
    // 조회수 메소드
    public void Cardcount() {

        this.cardcount++;

    }

}
