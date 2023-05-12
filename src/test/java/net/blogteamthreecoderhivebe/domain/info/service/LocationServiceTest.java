package net.blogteamthreecoderhivebe.domain.info.service;

import jakarta.persistence.EntityNotFoundException;
import net.blogteamthreecoderhivebe.domain.info.entity.Location;
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
class LocationServiceTest {
    @Autowired
    LocationService service;

    @DisplayName("지역 조회 성공")
    @CsvSource({"1", "2", "3", "4", "5"})
    @ParameterizedTest
    void findLocation(Long locationId) {
        Location location = service.findOne(locationId);
        assertThat(location.getId()).isEqualTo(locationId);
    }

    @DisplayName("존재하지 않는 지역을 조회할 경우 예외 발생")
    @Test
    void findLocationEx() {
        assertThatThrownBy(() -> service.findOne(10000L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("지역을 찾을 수 없습니다.");
    }
}
