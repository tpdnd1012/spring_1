package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.domain.post.PostEntity;
import spring.domain.post.PostRepository;
import spring.web.dto.PostDto;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // final 상수 => 자동 정의
public class PostService {

    private final PostRepository postRepository;

    // 1. 게시물 저장
    public void postsave(PostDto postDto){

        // 인수로 들어온 dto -> entity -> save 메소드에 넣어주기
        postRepository.save( postDto.toEntity() );

    }

    // 2. 게시물 모든 출력
    public Page<PostEntity> postlist(Pageable pageable, String keyword, String search){

        // 현재 페이지
        int page = (pageable.getPageNumber() == 0 ) ? 0 : (pageable.getPageNumber()-1);
                        // 논리 ? 참[T] : 거짓[F]

        // 현재 페이지 설정
        pageable = PageRequest.of( page , 5 , new Sort( Sort.Direction.DESC , "id"));
                // PageRequest.of( 현재페이지 , 페이지당 게시글수 , sort )

        // 현재 페이지의 게시물 찾기
        if(keyword == null || search == null ) {

            return postRepository.findAllsearch(keyword, search, pageable);

        }

        return postRepository.findAll(pageable);

                //        // 모든 entity 반환
                //        List<PostEntity> postEntities =  postRepository.findAll();
                //        // 모든 entity -> 모든 dto
                //        List<PostDto> postDtos = new ArrayList<>();
                //
                //        for( PostEntity temp : postEntities ){
                //
                //            PostDto postDto = PostDto.builder()
                //                    .id(temp.getId())
                //                    .title(temp.getTitle())
                //                    .contents(temp.getContents())
                //                    .name(temp.getName())
                //                    .count(temp.getCount())
                //                    .createDate( temp.getCreateDate())
                //                    .build();
                //            postDtos.add( postDto);
                //        }
                //        return postDtos;
    }

    // 3. 게시물 개별 출력
    public PostDto postget( Long id ){

        // 1. 해당 id의 엔티티 찾기
        Optional<PostEntity> optionalPostEntity = postRepository.findById(id);

        // 2. 엔티티를 가져오기
        PostEntity postEntity = optionalPostEntity.get();

        // 3. dto 변환
        return PostDto.builder()
                .id( postEntity.getId() )
                .title( postEntity.getTitle() )
                .contents(postEntity.getContents())
                .name(postEntity.getName() )
                .createDate( postEntity.getCreateDate() )
                .count( postEntity.getCount() ).build();
    }

    // 4. 조회수 처리
    @Transactional // 트랜잭션
    public void countup( Long id ){

        // 1.엔티티 찾기
        Optional<PostEntity> optionalPostEntity =  postRepository.findById(id);

        // 2. 엔티티 가져오기
        PostEntity postEntity = optionalPostEntity.get();

        // 3. 조회수 증가 메소드 호출
        postEntity.countup();

    }

    // 4. 게시물 수정
    @Transactional
    public void postupdate( PostDto updateDto ){

        // 1. 해당 엔티티 찾기
        Optional<PostEntity> optionalPostEntity = postRepository.findById(updateDto.getId());

        // 2. 엔티티 가져오기
        PostEntity postEntity = optionalPostEntity.get();

        // 3. 엔티티 수정처리
        postEntity.update( updateDto );

    }
    // 5. 게시물 삭제
    public void postdelete( Long id ){

        // 1. 엔티티 찾기
        Optional<PostEntity> optionalPostEntity = postRepository.findById(id);

        // 2. 엔티티 가져오기
        PostEntity postEntity = optionalPostEntity.get();

        // 3. 삭제 처리
        postRepository.delete( postEntity );

    }

}
