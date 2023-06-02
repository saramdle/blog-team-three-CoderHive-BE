package net.blogteamthreecoderhivebe.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;

@Getter
@ToString(exclude = {"post", "job"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecruitmentJob {
    @Id
    @Column(name = "recruitment_job_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    private int number;

    private int passNumber;

    @Builder
    public RecruitmentJob(Job job, int number, int passNumber) {
        this.job = job;
        this.number = number;
        this.passNumber = passNumber;
    }

    /**
     * 연관관계 편의 메서드
     */
    public void changePost(Post post) {
        this.post = post;
    }
}
