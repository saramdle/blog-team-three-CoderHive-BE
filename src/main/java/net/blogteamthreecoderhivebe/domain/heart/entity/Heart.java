package net.blogteamthreecoderhivebe.domain.heart.entity;

import jakarta.persistence.*;
import lombok.*;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;

@Builder
@Getter
@ToString(exclude = {"member", "post"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Heart {
    @Id
    @Column(name = "heart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // 연관 관계 편의 메소드
    public void addPost(Post post) {
        this.post = post;
        post.getHearts().add(this);
    }

    public void addMember(Member member) {
        this.member = member;
        member.getHearts().add(this);
    }
}
