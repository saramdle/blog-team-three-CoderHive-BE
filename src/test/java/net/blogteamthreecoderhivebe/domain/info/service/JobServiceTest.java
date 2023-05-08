package net.blogteamthreecoderhivebe.domain.info.service;

import jakarta.persistence.EntityNotFoundException;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class JobServiceTest {
    @Autowired
    JobService service;

    @DisplayName("직무 조회 성공")
    @CsvSource(value = {"1", "2", "3", "4", "5"})
    @ParameterizedTest
    void findJob(Long jobId) {
        Job job = service.findOne(jobId);
        assertThat(job.getId()).isEqualTo(jobId);
    }

    @DisplayName("존재하지 않는 직무를 조회할 경우 예외 발생")
    @Test
    void findJobEx() {
        assertThatThrownBy(() -> service.findOne(1000L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("직무를 찾을 수 없습니다.");
    }

}
