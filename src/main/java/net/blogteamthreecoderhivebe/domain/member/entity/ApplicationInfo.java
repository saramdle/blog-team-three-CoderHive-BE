package net.blogteamthreecoderhivebe.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentJob;

@Getter
@ToString(exclude = {"member", "recruitmentJob"})
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
    @JoinColumn(name = "recruitment_job_id")
    private RecruitmentJob recruitmentJob;

    @Enumerated(EnumType.STRING)
    private ApplicationResult applicationResult;

    private ApplicationInfo(Member member, RecruitmentJob recruitmentJob) {
        this.member = member;
        this.recruitmentJob = recruitmentJob;
        this.applicationResult = ApplicationResult.NON;
    }

    public static ApplicationInfo of(Member member, RecruitmentJob recruitmentJob) {
        return new ApplicationInfo(member, recruitmentJob);
    }
}
