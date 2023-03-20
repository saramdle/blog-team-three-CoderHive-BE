package net.blogteamthreecoderhivebe.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@ToString(exclude = {"user", "technology"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserTechnology {
    @Id
    @Column(name = "user_technology")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technology_id")
    private Technology technology;
}
