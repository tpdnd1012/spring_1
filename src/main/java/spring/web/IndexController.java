package spring.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import spring.service.MemberService;
import spring.web.dto.MemberDto;

import java.util.List;


@Controller //  해당 클래스 컨트롤러 주입
@RequiredArgsConstructor
public class IndexController {
    private final MemberService memberService ;
    @GetMapping("/") // 요청 URL 만들기  [ ip주소:port번호 ]
    public String index(Model model){ // 메소드
        // 회원목록 서비스
        List<MemberDto> memberDtoList = memberService.memberlist();
        // html에 데이터 전송 => model.addAttribute( "모델명" , 데이터 ) ;
        model.addAttribute( "memberDtoList" , memberDtoList);
        return "index"; //  문자열 반환
            // 타임리프가 html 를 찾는위치 : resources -> templates
    }
}
