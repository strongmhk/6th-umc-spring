package umc.spring.web.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDTO {
    @Getter
    public static class MissionRegisterDTO {
        @NotNull(message = "리워드는 필수 입력 값입니다.")
        @PositiveOrZero(message = "리워드는 0 이상의 값이어야 합니다.")
        private Integer reward;
        @NotNull(message = "마감일은 필수 입력 값입니다.")
        @FutureOrPresent(message = "마감일은 현재 이후의 날짜여야 합니다.")
        private LocalDate deadline;
        @NotBlank(message = "미션 내용은 필수 입력 값입니다.")
        private String missionSpec;
    }

    @Getter
    public static class ChallengeMissionDTO {
        private Long memberId;
        private Long missionId;
    }
}
