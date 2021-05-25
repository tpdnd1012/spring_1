package spring.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.service.MemberService;
import spring.web.dto.MemberDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor // final 사용시 => 자동 생성자 주입
public class MemberController {

    private final MemberService memberService; // 멤버 서비스 객체 선언
    private final HttpSession session; // 세션 객체 선언
    // ? : java <------> html

    //회원가입 페이지 요청
    @GetMapping("/membersignup") // 요청 URL 정의 => 메소드 실행
    public String membersignup() {
        return "membersignup"; // 타임리프 HTML 반환
    }

    // 회원가입 처리
    @PostMapping("/membersignup") // 서로다른 Mapping 이면 동일한 URL 가능
    public String membersignup_c(MemberDto memberDto) {
        // form 안에 있는 name 과  dto내 필드명이 동일한경우 자동 주입
        // jsp  Request : 객체
        // spring  HttpServletRequest request 객체 만들고 사용
        //        System.out.println( request.getParameter("name"));
        //        System.out.println( request.getParameter("memberid"));
        memberService.membersave(memberDto);
        return "redirect:/memberlogin"; // 회원가입 성공시 로그인페이지
    }

    // 로그인 페이지 요청
    @GetMapping("/memberlogin") // 요청 URL 정의 => 메소드 실행
    public String memberlogin() {
        return "memberlogin"; // 타임리프 HTML 반환
    }
    // 로그인 처리
    @PostMapping("/memberlogin") // 요청 URL 정의 => 메소드 실행
    public String memberlogin_c(MemberDto loginDto) {
        // 로그인 서비스 연결
        MemberDto memberDto = memberService.memberlogin(loginDto);
        if (memberDto != null) {
            // 로그인 성공 => 세션에 담기
            session.setAttribute("loginuser", memberDto);
            return "redirect:/"; // 타임리프 HTML 반환
        } else {
            // 로그인 실패
            return "redirect:/memberlogin"; // 다시 로그인페이지
        }
    }
    // 회원정보 페이지 요청
    @GetMapping("/memberinfo") // 요청 URL 정의 => 메소드 실행
    public String memberlogin(Model model) {
        // 현재 로그인된 정보
        MemberDto temp =  ( MemberDto) session.getAttribute("loginuser");
        // 현재 로그인된 정보의 dto 가져오기
        MemberDto memberDto =  memberService.memberfind( temp.getId() );
        // model 를 이용한 프론트에게 dto 넘기기
        model.addAttribute( "memberDto" , memberDto );
        return "memberinfo"; // 타임리프 HTML 반환
    }
    //
    @GetMapping("/memberlogout") // 요청 URL 정의 => 메소드 실행
    public String memberlogout(){
        session.invalidate(); // 세션 초기화
        return "redirect:/"; // 타임리프 HTML 반환
    }

    // 회원탈퇴 처리
    @GetMapping("/memberdelete")
    public String memberdelete(){
        // 세션 가져오기
        MemberDto memberDto =(MemberDto)session.getAttribute("loginuser");
        // 세션의 회원번호 가져오기
        Long id = memberDto.getId();
        // 삭제 서비스 넘기기
        memberService.memberdelete( id );
        // 세션 초기화
        session.invalidate();
        return "redirect:/"; // 해당 url 이동
    }

    // 회원 수정 페이지 요청
    @GetMapping("/memberupdate") // 요청 URL 정의 => 메소드 실행
    public String memberupdate( Model model){
        // 현재 로그인된 정보
        MemberDto temp =  ( MemberDto) session.getAttribute("loginuser");
        // 현재 로그인된 정보의 dto 가져오기
        MemberDto memberDto =  memberService.memberfind( temp.getId() );
        // model 를 이용한 프론트에게 dto 넘기기
        model.addAttribute( "memberDto" , memberDto );
        return "memberupdate"; // 타임리프 HTML 반환
    }
    // 회원 수정 처리
    @PostMapping("/memberupdate")
    public String memberupdate_c( MemberDto updateDto){
                                // form에 입력된 값 자동 주입
        // 세션 가져오기
        MemberDto memberDto =(MemberDto)session.getAttribute("loginuser");
        // 세션의 회원번호 가져오기
        Long id = memberDto.getId();
        memberService.memberupdate( id , updateDto );
        return "redirect:/memberinfo"; // URL 이동
    }

}
