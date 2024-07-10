package umc.spring.service.MissionService;

import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    Mission registerMissionToStore(Long storeId, MissionRequestDTO.MissionRegisterDTO request);
    MemberMission challengeMission(Long missionId, Long memberId);
    MemberMission completeMission(Long memberId, Long missionId);
}
