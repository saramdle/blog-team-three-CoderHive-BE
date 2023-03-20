package net.blogteamthreecoderhivebe.entity;

import jakarta.persistence.*;
import lombok.*;
import net.blogteamthreecoderhivebe.entity.constant.ApplyResult;

@Builder
@ToString(exclude = {"user", "postJob"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserApply {
    @Id
    @Column(name = "user_apply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_job_id")
    private PostJob postJob;

    @Enumerated(EnumType.STRING)
    private ApplyResult applyResult;
}
