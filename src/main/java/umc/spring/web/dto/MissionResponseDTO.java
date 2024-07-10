package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionRegisterResultDTO{
        Long missionId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreMissionDTO {
        private String storeName;
        private Integer reward;
        private LocalDate deadline;
        private String missionSpec;
        private LocalDate createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreMissionListDTO {
        private List<StoreMissionDTO> storeMissionDTOList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionDTO {
        private String storeName;
        private Integer reward;
        private LocalDate deadline;
        private String missionSpec;
        private LocalDate createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionListDTO {
        private List<MemberMissionDTO> memberMissionDTOList;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}
