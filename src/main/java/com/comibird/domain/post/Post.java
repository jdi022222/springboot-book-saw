package com.comibird.domain.post;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity // 코틀린 등의 새 언어로 롬복이 더이상 필요 없을 경우 쉽게 삭제하기 위해 클래스와 가깝게 둠
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // spring boot 2.0부터는 identity 옵션이 있어야만 자동 증가
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "text", nullable = false)
    private String content;

    private String author;

    @Builder // 기존 생성자 방식은 생성자 파라미터의 순서 변경시 컴파일 오류가 있기 때문에 builder 사용
    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
