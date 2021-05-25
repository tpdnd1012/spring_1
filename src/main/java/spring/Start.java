package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // Jpa 연결 확인
@SpringBootApplication // @ 어노테이션 주입 : 해당 클래스에게 스프링부트 프로그램 제공
public class Start {

    public static void main(String[] args) {

        SpringApplication.run(Start.class, args);
                            // 현재 클래스명.class, 메인 배열명

    }

}
