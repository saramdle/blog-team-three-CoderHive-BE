package net.blogteamthreecoderhivebe.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;
import net.blogteamthreecoderhivebe.domain.heart.entity.Heart;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;
import net.blogteamthreecoderhivebe.domain.info.entity.Location;
import net.blogteamthreecoderhivebe.domain.info.entity.Skill;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.post.constant.PostCategory;
import net.blogteamthreecoderhivebe.domain.post.constant.PostStatus;
import net.blogteamthreecoderhivebe.global.common.AuditingFields;

import java.util.ArrayList;
import java.util.Arrays;
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecruitmentSkill> recruitmentSkills = new ArrayList<>();

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

    /**
     * 연관관계 편의 메서드
     */
    public void addRecruitJob(RecruitmentJob recruitmentJob) {
        recruitmentJob.changePost(this);
        recruitmentJobs.add(recruitmentJob);
    }
    
    public void addRecruitSkill(RecruitmentSkill recruitmentSkill) {
        recruitmentSkill.changePost(this);
        recruitmentSkills.add(recruitmentSkill);
    }

    /**
     * 게시글의 총 좋아요 개수
     */
    public int getTotalHearts() {
        return hearts.size();
    }

    /**
     * 게시글의 사용 기술 목록
     */
    public List<String> getSkillDetails() {
        return recruitmentSkills.stream()
                .map(RecruitmentSkill::getSkill)
                .map(Skill::getDetail)
                .toList();
    }

    /**
     * 게시글을 좋아요한 회원 id 목록
     */
    public List<Long> getHeartMemberIds() {
        return hearts.stream()
                .map(Heart::getMember)
                .map(Member::getId)
                .toList();
    }

    /**
     * 출시 플랫폼 목록
     */
    public List<String> getPlatformList() {
        return Arrays.stream(platforms.split("&")).toList();
    }
}
