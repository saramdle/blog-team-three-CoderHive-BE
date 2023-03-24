package net.blogteamthreecoderhivebe.entity;

import jakarta.persistence.*;
import lombok.*;
import net.blogteamthreecoderhivebe.entity.constant.UserCareer;
import net.blogteamthreecoderhivebe.entity.constant.UserLevel;
import net.blogteamthreecoderhivebe.entity.constant.UserRole;

import java.util.Objects;

@Builder
@ToString(exclude = {"job", "technology"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends AuditingFields {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserLevel level;

    @Enumerated(EnumType.STRING)
    private UserCareer career;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String nickname;
    private String profileImageUrl;

    private String introduction;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User that)) return false;
        return this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
