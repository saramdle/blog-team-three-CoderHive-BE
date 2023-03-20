package net.blogteamthreecoderhivebe.entity;


import jakarta.persistence.*;
import lombok.*;

@Builder
@ToString(exclude = {"technology", "post"})
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
    @JoinColumn(name = "technology_id")
    private Technology technology;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
