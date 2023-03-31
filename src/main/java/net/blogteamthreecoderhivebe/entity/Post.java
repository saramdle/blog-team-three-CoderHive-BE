package net.blogteamthreecoderhivebe.entity;

import jakarta.persistence.*;
import lombok.*;
import net.blogteamthreecoderhivebe.entity.constant.PostCategory;
import net.blogteamthreecoderhivebe.entity.constant.PostStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@ToString(callSuper = true, exclude = {"member", "location"})
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends AuditingFields {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, name = "member_id")
    private Member member;

    @Builder.Default
    @OneToMany(mappedBy = "post")
    private List<LikePost> likingMembers = new ArrayList<>();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    private String title;

    @Column(length=1000)
    private String content;
    private String thumbImageUrl;

    @Enumerated(EnumType.STRING)
    private PostCategory postCategory;

    // use only Enum (constant) > platform
    private String platforms;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id")
    private Job job;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
