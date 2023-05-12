package net.blogteamthreecoderhivebe.domain.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.blogteamthreecoderhivebe.domain.info.entity.Skill;

@Getter
@ToString(exclude = {"skill", "post"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecruitmentSkill {
    @Id
    @Column(name = "recruitment_skill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private RecruitmentSkill(Skill skill, Post post) {
        this.skill = skill;
        this.post = post;
    }

    public static RecruitmentSkill of(Skill skill, Post post) {
        return new RecruitmentSkill(skill, post);
    }
}
