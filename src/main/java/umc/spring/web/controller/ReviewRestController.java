package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{storeId}/{memberId}")
    @Operation(summary = "회원의 가게 리뷰 작성 API",description = "회원이 가게에 리뷰를 작성하는 API입니다.")
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 id, path variable 입니다!"),
            @Parameter(name = "memberId", description = "회원의 id, path variable 입니다!"),
    })
    public ApiResponse<ReviewResponseDTO.ReviewRegisterResultDTO> registerReviewToStore(@RequestBody @Valid ReviewRequestDTO.ReviewRegisterDTO request,
                                                                                        @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                                        @ExistMember @PathVariable(name = "memberId") Long memberId) {
        Review newReview = reviewCommandService.registerReviewToStore(storeId, memberId, request);
        return ApiResponse.onSuccess(ReviewConverter.toRegisterResultDTO(newReview));
    }
}
