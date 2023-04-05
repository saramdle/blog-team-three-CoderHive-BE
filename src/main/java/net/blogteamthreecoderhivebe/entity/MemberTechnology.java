package net.blogteamthreecoderhivebe.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@ToString(exclude = {"member", "technology"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class MemberTechnology {
    @Id
    @Column(name = "member_technology_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technology_id")
    private Technology technology;
}
