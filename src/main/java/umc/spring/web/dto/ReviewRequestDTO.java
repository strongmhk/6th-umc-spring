package umc.spring.web.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;

public class ReviewRequestDTO {

    @Getter
    public static class ReviewRegisterDTO {
        @NotBlank(message = "제목은 필수 입력 값입니다.")
        private String title;
        @NotBlank(message = "내용은 필수 입력 값입니다.")
        private String body;
        @NotNull(message = "평점을 입력해주세요.")
        @DecimalMin(value = "0.0", message = "평점은 0.0 이상의 값이어야 합니다.")
        @DecimalMax(value = "5.0", message = "평점은 5.0 이하의 값이어야 합니다.")
        private Float score;
    }


}
