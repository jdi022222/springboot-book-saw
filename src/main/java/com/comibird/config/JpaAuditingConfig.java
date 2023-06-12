package com.comibird.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 별도의 설정파일로 구분하지 않으면 mockMvc를 사용한 테스트에서 에러가 발생 https://velog.io/@maketheworldwise/%ED%85%8C%EC%8A%A4%ED%8A%B8-%EC%BD%94%EB%93%9C%EC%97%90%EC%84%9C%EC%9D%98-JPA-Auditing-%EB%AC%B8%EC%A0%9C-IllegalArgumentException-JPA-metamodel-must-not-be-empty
@Configuration
public class JpaAuditingConfig {
}