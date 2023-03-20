package net.blogteamthreecoderhivebe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@ToString(callSuper = true, exclude = {"user", "post", "childReplys"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Reply extends AuditingFields {
    @Id
    @Column(name = "reply_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OrderBy("createdAt ASC") // 대댓글은 먼저 생성된 것부터 순차적으로 정렬
    @OneToMany(mappedBy = "parentReplyId", cascade = CascadeType.ALL) //부모가 지워지면 자식이 전부 지워지게
    private Set<Reply> childReplys = new LinkedHashSet<>(); //jpa 에서는 final 을 추천하지 않는다.

    @Setter
    @Column(updatable = false)
    private Long parentReplyId; //부모 댓글 ID ArticleComment parentComment 이런식으로 하면 양방향이 가능, 여기서는 단방향으로 설정
}
