package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Mission registerMissionToStore(Long storeId, MissionRequestDTO.MissionRegisterDTO request) {
        Store findStore = storeRepository.findById(storeId).get();
//                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND)); // TODO: 이거 없으니까 검증안됨..

        Mission newMission = MissionConverter.toMission(request, findStore);

        return missionRepository.save(newMission);
    }

    @Override
    public MemberMission challengeMission(Long missionId, Long memberId) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission findMission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission newMemberMission = MemberMissionConverter.toMemberMission(findMember, findMission);
        return memberMissionRepository.save(newMemberMission);
    }

    @Override
    public MemberMission completeMission(Long memberId, Long missionId) {
        MemberMission findMemberMission = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId);

//        // 1번 방법
//        MemberMission updatedMemberMission = findMemberMission.toBuilder()
//                .status(MissionStatus.COMPLETE)
//                .build();
//
//        return memberMissionRepository.save(updatedMemberMission);

        // 2번 방법
        MemberMission updatedMemberMission = findMemberMission.updateMissionStatus(MissionStatus.COMPLETE);

        return updatedMemberMission;
    }
}
