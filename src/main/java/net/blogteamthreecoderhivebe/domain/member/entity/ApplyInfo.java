package net.blogteamthreecoderhivebe.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplyResult;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitJob;

@Getter
@ToString(exclude = {"member", "recruitJob"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ApplyInfo {
    @Id
    @Column(name = "application_info_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_job_id")
    private RecruitJob recruitJob;

    @Enumerated(EnumType.STRING)
    private ApplyResult applyResult;

    private ApplyInfo(Member member, RecruitJob recruitJob) {
        this.member = member;
        this.recruitJob = recruitJob;
        this.applyResult = ApplyResult.APPLY;
    }

    public static ApplyInfo of(Member member, RecruitJob recruitJob) {
        return new ApplyInfo(member, recruitJob);
    }

    /**
     * 지원 결과 합격 처리
     */
    public void modifyResultToPass() {
        this.applyResult = ApplyResult.PASS;
    }
}
