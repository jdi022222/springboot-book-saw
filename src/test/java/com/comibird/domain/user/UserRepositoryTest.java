package com.comibird.domain.user;

import com.comibird.domain.post.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    User user1;

    @BeforeEach
    void setUp() {
        user1 = User.builder()
                .name("user1")
                .email("user1@email.com")
                .picture("picture1")
                .role(Role.USER)
                .build();
    }

    @AfterEach
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    void 이메일로_회원을_조회할_수_있다() {
        // given
        userRepository.save(user1);
        String email = "user1@email.com";

        // when
        User findUser = userRepository.findByEmail(email).get();

        // then
        Assertions.assertThat(findUser).isEqualTo(user1);
    }
}