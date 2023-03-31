package net.blogteamthreecoderhivebe.repository;

import net.blogteamthreecoderhivebe.config.TestJpaConfig;
import net.blogteamthreecoderhivebe.config.TestQueryDslConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MemberTechnologyRepository 테스트")
@DataJpaTest
@Import({TestJpaConfig.class, TestQueryDslConfig.class})
class MemberTechnologyRepositoryTest {

    @Autowired
    private MemberTechnologyRepository memberTechnologyRepository;

    @DisplayName("멤버 id 로 skills 정보 가져오기")
    @Test
    void getSkills() {
        List<String> strings = memberTechnologyRepository.searchTechnology(1L);
        System.out.println(strings);
    }


}