package com.comibird.web;

import com.comibird.domain.post.Post;
import com.comibird.domain.post.PostRepository;
import com.comibird.web.dto.PostSaveRequestDto;
import com.comibird.web.dto.PostUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    public void tearDown() throws Exception {
        postRepository.deleteAll();
    }

    @Test
    public void Post_등록된다() throws Exception {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String author = "test@gmail.com";

        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.
                postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Post> posts = postRepository.findAll();
        Post resultPost = posts.get(0);
        assertThat(resultPost.getTitle()).isEqualTo(title);
        assertThat(resultPost.getContent()).isEqualTo(content);
        assertThat(resultPost.getAuthor()).isEqualTo(author);
    }

    @Test
    public void Post_수정된다() throws Exception {
        //given
        Post savedPost = postRepository.save(
                Post.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                        .build());

        Long updatedId = savedPost.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostUpdateRequestDto requestDto = PostUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updatedId;
        HttpEntity<PostUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Post> posts = postRepository.findAll();
        Post resultPost = posts.get(0);
        assertThat(resultPost.getTitle()).isEqualTo(expectedTitle);
        assertThat(resultPost.getContent()).isEqualTo(expectedContent);
    }
}