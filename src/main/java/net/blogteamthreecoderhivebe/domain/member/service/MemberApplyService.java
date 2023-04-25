package net.blogteamthreecoderhivebe.domain.member.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.member.entity.MemberApply;
import net.blogteamthreecoderhivebe.domain.member.repository.MemberApplyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberApplyService {
    private final MemberApplyRepository memberApplyRepository;

    public List<MemberApply> searchApplyPost(Long memberId) {
        return memberApplyRepository.findByMember_Id(memberId);
    }


}
