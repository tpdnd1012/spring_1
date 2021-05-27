package spring.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import spring.service.CardService;
import spring.web.dto.CardDto;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class Cardcontroller {

    private final CardService cardService;
    
    // 카드 등록 페이지 요청
    @GetMapping("/cardwrite")
    public String cardwrite() {

        return "cardwrite";

    }
    
    // 카드 등록 처리
    @PostMapping("/cardwrite")
    public String cardwrite_c(@RequestParam("cardpicture") MultipartFile files, HttpServletRequest request) {
                                                            // multipart 사용중인 form 자동주입x
                                                                // file : request 가져오기x
                                                                // => MultipartFile 사용하기
                                                            // flie 변수 설정 : @RequestParam("변수명")
        
        // 이미지 저장 [ 업로드 ]
        String 업로드경로 = "C:\\Users\\User\\IdeaProjects\\spring_1\\src\\main\\resources\\static\\images";
            // : \ : 제어문 \n \t
            // \\ : 경로
        String 파일경로 = 업로드경로 + "\\" + files.getOriginalFilename();

        // 업로드 : 파일경로 해당하는 파일을 객체화
        try {
            files.transferTo(new File(파일경로)); // 실제로 파일 저장
        } catch (IOException e) {
            e.printStackTrace();
        }

        CardDto cardDto = CardDto.builder()
                .cardname(request.getParameter("cardname"))
                .cardcompany(request.getParameter("cardcompany"))
                .cardlink(request.getParameter("cardlink"))
                .cardpicture(files.getOriginalFilename()).build();
                                    // 파일명 저장

        cardService.cardsave(cardDto);

        return "index";

    }

}
