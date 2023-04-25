package net.blogteamthreecoderhivebe.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentJob;

@Builder
@ToString(exclude = {"member", "recruitmentJob"})
@Getter
@AllArgsConstructor
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
    @JoinColumn(name = "post_job_id")
    private RecruitmentJob recruitmentJob;

    @Enumerated(EnumType.STRING)
    private ApplicationResult applicationResult;
}
