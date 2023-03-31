package net.blogteamthreecoderhivebe.entity;

import jakarta.persistence.*;
import lombok.*;
import net.blogteamthreecoderhivebe.entity.constant.MemberCareer;
import net.blogteamthreecoderhivebe.entity.constant.MemberLevel;
import net.blogteamthreecoderhivebe.entity.constant.MemberRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@ToString(callSuper = true, exclude = {"job", "listPosts"})
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

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<LikePost> listPosts = new ArrayList<>();

    private String nickname;
    private String profileImageUrl;

    @Column(length=1000)
    private String introduction;

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
