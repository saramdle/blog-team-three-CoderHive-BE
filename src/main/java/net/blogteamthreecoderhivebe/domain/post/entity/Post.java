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
import java.util.Objects;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true, exclude = {"member", "recruitmentJobs", "hearts", "location", "job"})
@Table(indexes = @Index(columnList = "modifiedAt DESC"))
@Entity
public class Post extends AuditingFields {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, name = "member_id")
    private Member member;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Builder.Default
    @OneToMany(mappedBy = "post")
    private List<Heart> hearts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
