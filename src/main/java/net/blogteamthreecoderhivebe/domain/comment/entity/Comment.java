package net.blogteamthreecoderhivebe.domain.comment.entity;

import jakarta.persistence.*;
import lombok.*;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.global.common.BaseEntity;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@ToString(callSuper = true, exclude = {"member", "post", "childComments"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseEntity {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(length = 1000)
    private String content;

    @OrderBy("createdAt ASC") // 먼저 생성된 것부터 순차적으로 정렬
    @OneToMany(mappedBy = "parentCommentId", cascade = CascadeType.ALL, orphanRemoval = true) // 부모가 삭제되면 자식 모두 삭제
    private Set<Comment> childComments = new LinkedHashSet<>();

    @Setter
    @Column(updatable = false)
    private Long parentCommentId; // 부모 댓글 ID ArticleComment parentComment 이런식으로 하면 양방향이 가능, 여기서는 단방향으로 설정

    @Builder
    public Comment(Long id, Member member, Post post, String content, Long parentCommentId) {
        this.id = id;
        this.member = member;
        this.post = post;
        this.content = content;
        this.parentCommentId = parentCommentId;
    }
}
