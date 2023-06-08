package net.blogteamthreecoderhivebe.domain.post.service.vo;

import java.util.Objects;

public record RecruitJobResult(
        int totalNumber, // 총 모집 인원
        int passNumber // 모집 완료된 인원
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecruitJobResult that = (RecruitJobResult) o;
        return totalNumber == that.totalNumber && passNumber == that.passNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalNumber, passNumber);
    }
}
