package umc.spring.web.dto;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.validation.annotation.ExistStore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    //
    @PostMapping("/{storeId}")
    @Operation(summary = "가게에 미션 추가하기 API",description = "특정 가게에 미션을 추가하는 API입니다.")
    public ApiResponse<MissionResponseDTO.MissionRegisterResultDTO> registerMissionToStore(@RequestBody @Valid MissionRequestDTO.MissionRegisterDTO request,
                                                                                           @ExistStore @PathVariable("storeId") Long storeId) {
        Mission newMission = missionCommandService.registerMissionToStore(storeId, request);
        return ApiResponse.onSuccess(MissionConverter.toMissionRegisterResultDTO(newMission));
    }

    @PostMapping("/{missionId}/{memberId}")
    @Operation(summary = "미션 도전하기 API",description = "특정 미션을 회원과 연결하며, 진행 상태를 도전중으로 바꿉니다.")
    public ApiResponse<MemberMissionResponseDTO.UpdateMissionStatusResultDTO> challengeMission(@RequestBody @Valid MissionRequestDTO.ChallengeMissionDTO request,
                                                                                               @ExistMission @PathVariable("missionId") Long missionId,
                                                                                               @ExistMember @PathVariable("memberId") Long memberId) {
        MemberMission newMemberMission = missionCommandService.challengeMission(missionId, memberId);
        return ApiResponse.onSuccess(MemberMissionConverter.toUpdateMissionStatusResultDTO(newMemberMission));
    }
}
