package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDTO request) {
        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .build();
    }

    public static MissionResponseDTO.MemberMissionDTO toMemberMissionDTO(MemberMission memberMission) {
        return MissionResponseDTO.MemberMissionDTO.builder()
                .storeName(memberMission.getMission().getStore().getName())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .createdAt(LocalDate.from(memberMission.getMission().getCreatedAt()))
                .build();
    }

    public static MissionResponseDTO.MemberMissionListDTO toMemberMissionListDTO(Page<MemberMission> memberMissionList) {
        List<MissionResponseDTO.MemberMissionDTO> memberMissionDTOList = memberMissionList.stream()
                .map(MemberConverter::toMemberMissionDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MemberMissionListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(memberMissionDTOList.size())
                .memberMissionDTOList(memberMissionDTOList)
                .build();
    }
}
