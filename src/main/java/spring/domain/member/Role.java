package spring.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    // 열거형 : 순서가 있는 상수
    ADMIN( "ROLE_ADMIN" , "관리자"), MEMBER("ROLE_MEMBER","일반회원");
    private final String key; // 변수
    private final String title; // 변수
}