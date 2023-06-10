package com.comibird.web.domain.post;

import com.comibird.domain.post.Post;
import com.comibird.domain.post.PostRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    // 단위 테스트가 끝날 때마다 수행되는 메서드
    // 여러 테스트 동시 수행시 H2에 데이터가 남아 테스트 실패할 수도 있음
    @After
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
}