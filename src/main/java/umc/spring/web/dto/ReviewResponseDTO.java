package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewRegisterResultDTO {
        Long reviewId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewListDTO {
        List<MyReviewDTO> myReviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewDTO {
        String ownerNickname;
        Float score;
        String title;
        String body;
        LocalDate createdAt;
//        List<ReviewImage> reviewImg; TODO: 이건 나중에
    }
}
