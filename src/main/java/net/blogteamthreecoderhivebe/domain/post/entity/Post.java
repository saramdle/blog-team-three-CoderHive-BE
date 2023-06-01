package net.blogteamthreecoderhivebe.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;
import net.blogteamthreecoderhivebe.domain.heart.entity.Heart;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;
import net.blogteamthreecoderhivebe.domain.info.entity.Location;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.constant.PostStatus;
import net.blogteamthreecoderhivebe.global.common.AuditingFields;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = false, of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, exclude = {"member", "job", "location", "hearts", "recruitmentJobs"})
@Table(indexes = @Index(columnList = "modifiedAt DESC"))
@Entity
public class Post extends AuditingFields {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Heart> hearts = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecruitmentJob> recruitmentJobs = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private PostCategory postCategory;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    private String title;

    @Column(length = 1000)
    private String content;

    private String thumbImageUrl;

    private String platforms; // use only Enum (constant) > platform

    @Builder
    public Post(Member member, Job job, Location location, PostCategory postCategory, PostStatus postStatus,
                String title, String content, String thumbImageUrl, String platforms) {
//        Assert.notNull(member, "member must not be null");
//        Assert.notNull(job, "job must not be null");
//        Assert.notNull(location, "location must not be null");

        this.member = member;
        this.job = job;
        this.location = location;
        this.postCategory = postCategory;
        this.postStatus = postStatus;
        this.title = title;
        this.content = content;
        this.thumbImageUrl = thumbImageUrl;
        this.platforms = platforms;
    }
}
