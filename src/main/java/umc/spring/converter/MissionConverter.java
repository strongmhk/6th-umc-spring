package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {

    public static MissionResponseDTO.MissionRegisterResultDTO toMissionRegisterResultDTO(Mission mission) {
        return MissionResponseDTO.MissionRegisterResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.MissionRegisterDTO request, Store store) {
        return Mission.builder()
                .store(store)
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .build();
    }
}
