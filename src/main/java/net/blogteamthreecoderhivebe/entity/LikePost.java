package net.blogteamthreecoderhivebe.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@ToString(exclude = {"member", "post"})
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
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    // 연관 관계 편의 메소드
    public void addPost(Post post) {
        this.post = post;
        post.getLikingMembers().add(this);
    }

    public void addMember(Member member) {
        this.member = member;
        member.getListPosts().add(this);
    }
}
