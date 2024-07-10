package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Page<Review> getMyReviewList(Long memberId, Integer page) {
        Member findMember = findMemberByMemberId(memberId);

        Page<Review> memberPage = reviewRepository.findAllByMember(findMember, PageRequest.of(page, 10));
        return memberPage;
    }

    @Override
    public Page<MemberMission> getMemberMissionList(Long memberId, Integer page) {
        Member findMember = findMemberByMemberId(memberId);
        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllByMemberJoinFetch(findMember, PageRequest.of(0, 10));

        return memberMissionPage;
    }

    private Member findMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId).get();
    }
}
