package net.blogteamthreecoderhivebe.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@ToString(exclude = {"user", "post"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class LikePost {
    @Id
    @Column(name = "like_post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;


}
