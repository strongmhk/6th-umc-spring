package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.repository.MissionRepository;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public boolean existMissionByMissionId(Long missionId) {
        return missionRepository.existsById(missionId);
    }
}
