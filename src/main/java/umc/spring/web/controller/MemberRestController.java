package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.web.dto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "특정 회원의 리뷰 목록 조회 API",description = "특정 회원의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<ReviewResponseDTO.MyReviewListDTO> getMyReviewList(@ExistMember @PathVariable("memberId") Long memberId,
                                                                          @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Review> myReviewList = memberQueryService.getMyReviewList(memberId, page);

        return ApiResponse.onSuccess(ReviewConverter.toGetMyReviewListDTO(myReviewList));
    }


    @GetMapping("/{memberId}/missions")
    @Operation(summary = "특정 회원의 진행중인 미션 목록 조회 API",description = "특정 회원의 진행중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<MissionResponseDTO.MemberMissionListDTO> getMissionList(@ExistMember @PathVariable("memberId") Long memberId,
                                                                               @CheckPage @RequestParam(name = "page") Integer page) {
        Page<MemberMission> memberMissionList = memberQueryService.getMemberMissionList(memberId, page);

        return ApiResponse.onSuccess(MemberConverter.toMemberMissionListDTO(memberMissionList));
    }


    @PutMapping("/{memberId}/{missionId}")
    @Operation(summary = "진행중인 미션 완료 상태로 변경  API",description = "미션의 상태를 진행 완료로 변경합니다.")
    @Parameters({
            @Parameter(name = "missionId", description = "미션의 아이디, path variable 입니다!"),
            @Parameter(name = "memberId", description = "회원의 아이디, path variable 입니다!"),
    })
    public ApiResponse<MemberMissionResponseDTO.UpdateMissionStatusResultDTO> missionComplete(@ExistMission @PathVariable("missionId") Long missionId,
                                                                                              @ExistMember @PathVariable("memberId") Long memberId) {
        MemberMission updatedMemberMission = missionCommandService.completeMission(memberId, missionId);
        return ApiResponse.onSuccess(MemberMissionConverter.toUpdateMissionStatusResultDTO(updatedMemberMission));
    }

}
