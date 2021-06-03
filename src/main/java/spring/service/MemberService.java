package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.domain.member.MemberEntity;
import spring.domain.member.MemberRepository;
import spring.web.dto.MemberDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // 해당 클래스는 서비스 관련된 데이터 제공
@RequiredArgsConstructor
public class MemberService {
    // 해당 엔티티를 연결 해줄 repository
    private final MemberRepository memberRepository ; // member 엔티티 연결 객체
        // final : 상수 : 고정값 => 정의

    // 회원 저장
    @Transactional // javax
    public Long membersave( MemberDto memberDto ){

       return memberRepository.save( memberDto.toEntity()  ).getId();
                    // sql insert ----> save( )
    }

    // 회원 출력
    @Transactional // javax
    public List<MemberDto> memberlist(){

        // 모든 엔티티 가져오기 . findall()
        List<MemberEntity> memberEntityList = memberRepository.findAll(); // sql select -------> 모든 레코드 호출
        //  dto 리스트 선언
        List<MemberDto> memberDtoList = new ArrayList<>();

        for( MemberEntity temp :  memberEntityList ) {
            // for(  클래스명 임시객체명 : 리스트명 )  : 리스트내 개수만큼 임시객체에 하나씩 대입

                                // for( int i = 0 ; i<memberEntityList.size ; i++ ){
                                //   MemberEntity temp = memberEntityList[i];
                                //      }
            // 엔티티 ---> dto
            MemberDto memberDto = MemberDto.builder()
                    .id( temp.getId() )
                    .memberid( temp.getMemberid() )
                    .password(temp.getPassword())
                    .name( temp.getName())
                    .email( temp.getEmail()).build();
            memberDtoList.add( memberDto);
        }
        return memberDtoList;
    }

    // 회원 로그인
    @Transactional // javax
    public MemberDto memberlogin( MemberDto logindto){
        // 1. 모든 회원 가져오기
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        // 2. 로그인에 입력된 아이디와 비밀번호 찾기 
        for( MemberEntity temp : memberEntityList ){
            if( temp.getMemberid().equals(logindto.getMemberid())
                    && temp.getPassword().equals( logindto.getPassword() )){
                // 3. 찾은 dto 반환
                MemberDto memberDto = MemberDto.builder()
                        .id( temp.getId() )
                        .memberid( temp.getMemberid() )
                        .name( temp.getName())
                        .email( temp.getEmail() ).build();
                return memberDto;
            }
        }
        return null; // 못찾았으면 null ;
    }

    // 회원 개별 찾기
    @Transactional // javax
    public MemberDto memberfind( Long id){
        // 1. 해당 회원번호의 엔티티 찾기
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
                                                            // findById 의 리턴값이 Optional
        // 2. 찾은 엔티티 가져오기
        MemberEntity memberEntity = optionalMemberEntity.get();
        // 3. 엔티티 => dto로 변환후 => dto 리턴
        MemberDto memberDto = MemberDto.builder()
                .id( memberEntity.getId() )
                .memberid( memberEntity.getMemberid() )
                .name( memberEntity.getName())
                .email( memberEntity.getEmail() ).build();

        return  memberDto;
    }

    // 회원 탈퇴
    @Transactional // javax
    public int memberdelete( String email ){

         Optional<MemberEntity> optionalMemberEntity = memberRepository.findByemail(email);

         MemberEntity memberEntity = optionalMemberEntity.get();

         memberRepository.delete( memberEntity ); // 삭제 쿼리

        return  1;
    }

    // 회원 수정 처리
    @Transactional // javax
    public int memberupdate( String email , MemberDto updateDto ){

        // 1. db에서 회원찾기
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByemail(email);
        // 2. 찾았으면 엔티티 가져오기
        MemberEntity memberEntity = optionalMemberEntity.get();
        // 3. 업데이트 처리 => 메소드 호출
        memberEntity.update( updateDto );

        return  1;
    }

}
