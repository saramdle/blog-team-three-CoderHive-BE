package net.blogteamthreecoderhivebe.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitJob;

@Getter
@ToString(exclude = {"member", "recruitJob"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ApplicationInfo {
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
    private ApplicationResult applicationResult;

    private ApplicationInfo(Member member, RecruitJob recruitJob) {
        this.member = member;
        this.recruitJob = recruitJob;
        this.applicationResult = ApplicationResult.APPLY;
    }

    public static ApplicationInfo of(Member member, RecruitJob recruitJob) {
        return new ApplicationInfo(member, recruitJob);
    }

    /**
     * 지원 결과 합격 처리
     */
    public void modifyResultToPass() {
        this.applicationResult = ApplicationResult.PASS;
    }
}
