package com.comibird.config.auth.dto;

import com.comibird.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
/*
만약 User클래스를 그대로 사용하게 되고 User 클래스에 직렬화를 넣으면
다른 엔티티와 관계가 형성될 경우 자식들까지 직렬화 대상에 포함되게 됨.
따라서 직렬화 기능을 가진 세션 Dto를 생성하는 것이 운영 및 유지보수 측면에서 도움
 */
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
