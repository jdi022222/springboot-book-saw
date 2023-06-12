package com.comibird.domain.post;

import com.comibird.domain.post.Post;
import com.comibird.domain.post.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    // 단위 테스트가 끝날 때마다 수행되는 메서드
    // 여러 테스트 동시 수행시 H2에 데이터가 남아 테스트 실패할 수도 있음
    @AfterEach
    public void cleanUp() {
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String author = "test@gmail.com";

        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        postRepository.save(post);

        //when
        List<Post> posts = postRepository.findAll();

        //then
        Post resultPost = posts.get(0);
        assertThat(resultPost.getTitle()).isEqualTo(title);
        assertThat(resultPost.getContent()).isEqualTo(content);
        assertThat(resultPost.getAuthor()).isEqualTo(author);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2023, 6, 12, 0, 0, 0);

        String title = "테스트 게시글";
        String content = "테스트 본문";
        String author = "test@gmail.com";

        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        postRepository.save(post);

        //when
        List<Post> posts = postRepository.findAll();

        //then
        Post resultPost = posts.get(0);
        assertThat(resultPost.getCreateDate()).isAfter(now);
        assertThat(resultPost.getModifiedDate()).isAfter(now);
    }
}