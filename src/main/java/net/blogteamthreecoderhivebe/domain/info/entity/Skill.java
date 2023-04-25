package net.blogteamthreecoderhivebe.domain.info.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Skill {
    @Id
    @Column(name = "skill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String detail;
}
