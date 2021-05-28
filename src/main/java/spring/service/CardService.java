package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.domain.card.CardEntity;
import spring.domain.card.CardRepository;
import spring.web.dto.CardDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    
    // 카드 등록
    public void cardsave(CardDto cardDto) {

        cardRepository.save(cardDto.toEntity());

    }
    
    // 모든 카드 출력
    public List<CardDto> cardDtolist() {

        List<CardEntity> cardEntities = cardRepository.findAll();

        List<CardDto> cardDtoList = new ArrayList<>();

        for(CardEntity entity : cardEntities) {

            CardDto cardDto = CardDto.builder()
                    .id(entity.getId())
                    .cardname(entity.getCardname())
                    .cardcompany(entity.getCardcompany())
                    .cardpicture(entity.getCardpicture())
                    .cardcount(entity.getCardcount()).build();

            cardDtoList.add(cardDto);

        }
        return cardDtoList;
    }
    
    // 카드 조건 출력
    
    // 카드 수정
    
    // 카드 삭제
    public void carddelete(Long id) {

        // 1. 엔티티 찾기
        Optional<CardEntity> optionalCardEntity = cardRepository.findById(id);

        // 2. 엔티티 가져오기
        CardEntity cardEntity = optionalCardEntity.get();

        // 3. 삭제처리
        cardRepository.delete(cardEntity);

    }

}
