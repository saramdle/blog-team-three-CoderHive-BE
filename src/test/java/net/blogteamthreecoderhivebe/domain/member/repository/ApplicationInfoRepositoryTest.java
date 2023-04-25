package net.blogteamthreecoderhivebe.domain.member.repository;

import net.blogteamthreecoderhivebe.domain.member.entity.ApplicationInfo;
import net.blogteamthreecoderhivebe.global.config.TestJpaConfig;
import net.blogteamthreecoderhivebe.global.config.TestQueryDslConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DisplayName("ApplicationInfoRepository 테스트")
@Transactional
@DataJpaTest
@Import({TestJpaConfig.class, TestQueryDslConfig.class})
public class ApplicationInfoRepositoryTest {
    @Autowired
    private ApplicationInfoRepository applicationInfoRepository;

    @DisplayName("member id로 지원한 공고 확인")
    @Test
    public void searchApplyPost() {
        List<ApplicationInfo> byMemberId = applicationInfoRepository.findByMember_Id(1L);
        for (ApplicationInfo applicationInfo : byMemberId) {
            System.out.println(applicationInfo);
        }


    }

}
