package spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import spring.service.Oauth2Service;

@Configuration // 외부 라이브러리 설정 값 변경시
@EnableWebSecurity // 시큐리티 정의시 :  extends WebSecurityConfigurerAdapter
@RequiredArgsConstructor // final 필드에 자동정의
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Oauth2Service oauth2Service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/adminpage").hasRole("ADMIN")
                .antMatchers("/member/myinfo").hasRole("MEMBER")
                .antMatchers("/**").permitAll() // 모든 접근 허용

        .and()
                .logout() // 로그아웃 관련 설정
                .logoutRequestMatcher( new AntPathRequestMatcher("/logout")) // 로그아웃 url 설정
                .logoutSuccessUrl("/") // 로그아웃 성공시
                .invalidateHttpSession(true) // 세션 초기화

        .and()
                .csrf() // 사이트 간 요청 위조  // html 입력에 관련된 페이지는 무시
                .ignoringAntMatchers("/h2-console/**")
                .ignoringAntMatchers("/postwrite") // 게시판 글쓰기
                .ignoringAntMatchers("/postupdate") //게시판 수정
                .ignoringAntMatchers("/membersignup") // 회원가입
                .ignoringAntMatchers("/memberlogin") // 로그인
                .ignoringAntMatchers("/memberupdate") // 회원수정

        .and()
                .exceptionHandling() // 예외 발생했을 경우 처리
                .accessDeniedPage("/error") // 예외 페이지 설정

        .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oauth2Service);

        http.headers().frameOptions().disable();
    }
}
