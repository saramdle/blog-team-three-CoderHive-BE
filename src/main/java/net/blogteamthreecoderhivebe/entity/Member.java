package net.blogteamthreecoderhivebe.entity;

import jakarta.persistence.*;
import lombok.*;
import net.blogteamthreecoderhivebe.entity.constant.MemberCareer;
import net.blogteamthreecoderhivebe.entity.constant.MemberLevel;
import net.blogteamthreecoderhivebe.entity.constant.MemberRole;

import java.util.Objects;

@Builder
@ToString(exclude = "job")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends AuditingFields {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private MemberLevel level;

    @Enumerated(EnumType.STRING)
    private MemberCareer career;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    private String nickname;
    private String profileImageUrl;

    private String introduction;

    public static Member of (Job job, String email, MemberLevel level,
                MemberCareer career, String nickname) {
        return new Member(null, job, email, level, career, MemberRole.USER, nickname, null, null);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member that)) return false;
        return this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
