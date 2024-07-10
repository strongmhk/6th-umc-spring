package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDTO.ReviewRegisterResultDTO toRegisterResultDTO(Review review) {
        return ReviewResponseDTO.ReviewRegisterResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }


    public static Review toReview(ReviewRequestDTO.ReviewRegisterDTO request, Member member, Store store) {
        return Review.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .score(request.getScore())
                .member(member)
                .store(store)
                .build();
    }

    public static ReviewResponseDTO.MyReviewDTO toGetMyReviewDTO(Review review) {
        return ReviewResponseDTO.MyReviewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .title(review.getTitle())
                .body(review.getBody())
                .createdAt(LocalDate.from(review.getCreatedAt()))
                .build();
    }

    public static ReviewResponseDTO.MyReviewListDTO toGetMyReviewListDTO(Page<Review> myReviewList) {
        List<ReviewResponseDTO.MyReviewDTO> myReviewDTOList = myReviewList.stream()
                .map(ReviewConverter::toGetMyReviewDTO)
                .collect(Collectors.toList());

        return ReviewResponseDTO.MyReviewListDTO.builder()
                .isLast(myReviewList.isLast())
                .isFirst(myReviewList.isFirst())
                .totalPage(myReviewList.getTotalPages())
                .totalElements(myReviewList.getTotalElements())
                .listSize(myReviewDTOList.size())
                .myReviewList(myReviewDTOList)
                .build();
    }
}

