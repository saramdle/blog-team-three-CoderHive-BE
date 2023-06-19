package net.blogteamthreecoderhivebe.domain.member.repository.querydsl;

import net.blogteamthreecoderhivebe.domain.member.constant.ApplicationResult;
import net.blogteamthreecoderhivebe.domain.member.entity.Member;
import net.blogteamthreecoderhivebe.domain.post.entity.Post;
import net.blogteamthreecoderhivebe.domain.post.entity.RecruitmentJob;

import java.util.List;
import java.util.Optional;

public interface ApplyInfoCustom {
    Optional<ApplicationResult> findApplyResult(Member member, RecruitmentJob recruitmentJob);

    List<Member> findPassMembers(Post post);
}
