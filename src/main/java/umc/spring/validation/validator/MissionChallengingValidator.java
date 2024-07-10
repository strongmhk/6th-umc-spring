package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.validation.annotation.AlreadyChallenging;
import umc.spring.web.dto.MissionRequestDTO;

@Component
@RequiredArgsConstructor
public class MissionChallengingValidator implements ConstraintValidator<AlreadyChallenging, MissionRequestDTO.ChallengeMissionDTO> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(AlreadyChallenging constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MissionRequestDTO.ChallengeMissionDTO value, ConstraintValidatorContext context) {
        MemberMission findMemberMission = memberMissionRepository.findByMemberIdAndMissionId(value.getMemberId(), value.getMissionId());

        // CHALLENGING이면, 유효하지 않은 요청
        boolean isValid = (findMemberMission.getStatus() == MissionStatus.CHALLENGING) ? false : true;

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.ALREADY_CHALLENGING.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
