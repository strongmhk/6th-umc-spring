package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.ReviewRequestDTO;


@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public Review registerReviewToStore(Long storeId, Long memberId, ReviewRequestDTO.ReviewRegisterDTO request) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Store findStore = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Review newReview = ReviewConverter.toReview(request, findMember, findStore);
        return reviewRepository.save(newReview);
    }
}
