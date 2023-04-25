package net.blogteamthreecoderhivebe.domain.member.service;

import lombok.RequiredArgsConstructor;
import net.blogteamthreecoderhivebe.domain.member.entity.ApplicationInfo;
import net.blogteamthreecoderhivebe.domain.member.repository.ApplicationInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ApplicationInfoService {
    private final ApplicationInfoRepository applicationInfoRepository;

    public List<ApplicationInfo> searchApplyPost(Long memberId) {
        return applicationInfoRepository.findByMember_Id(memberId);
    }


}
