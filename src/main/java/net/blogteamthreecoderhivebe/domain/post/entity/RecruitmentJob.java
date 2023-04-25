package net.blogteamthreecoderhivebe.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;

@Builder
@Getter
@ToString(exclude = {"post", "job"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    public void addPost(Post post) {
        this.post = post;
        post.getRecruitmentJobs().add(this);
    }
}
