package net.blogteamthreecoderhivebe.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import net.blogteamthreecoderhivebe.domain.heart.entity.Heart;
import net.blogteamthreecoderhivebe.domain.info.entity.Job;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberCareer;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberLevel;
import net.blogteamthreecoderhivebe.domain.member.constant.MemberRole;
import net.blogteamthreecoderhivebe.global.common.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static net.blogteamthreecoderhivebe.domain.member.constant.MemberRole.GUEST;
import static net.blogteamthreecoderhivebe.domain.member.constant.MemberRole.USER;

@Builder
@Getter
@ToString(callSuper = true, exclude = {"job", "hearts"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Member extends BaseTimeEntity {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String nickname;
    private String profileImageUrl;

    @Column(length = 1000)
    private String introduction;

    @Enumerated(EnumType.STRING)
    private MemberLevel level;

    @Enumerated(EnumType.STRING)
    private MemberCareer career;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @Builder.Default
    @OneToMany(mappedBy = "member")
    private List<Heart> hearts = new ArrayList<>();

    // TODO : Member update 메소드 생성
    // expected 1) 회원가입 기능
    // expected 2) 프로필 수정 기능
    public void update(String nickname, MemberLevel level, MemberCareer career, Job job) {
        this.nickname = nickname;
        this.job = job;
        this.memberRole = USER;
        this.level = level;
        this.career = career;
    }

    public boolean isNotGuest() {
        return memberRole != GUEST;
    }

    public boolean isUser() {
        return memberRole == USER;
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
