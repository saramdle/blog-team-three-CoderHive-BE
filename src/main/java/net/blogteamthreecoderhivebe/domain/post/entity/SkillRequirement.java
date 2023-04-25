package net.blogteamthreecoderhivebe.domain.post.entity;


import jakarta.persistence.*;
import lombok.*;
import net.blogteamthreecoderhivebe.domain.info.entity.Skill;

@Builder
@ToString(exclude = {"skill", "post"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class SkillRequirement {
    @Id
    @Column(name = "skill_requirement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Skill skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
