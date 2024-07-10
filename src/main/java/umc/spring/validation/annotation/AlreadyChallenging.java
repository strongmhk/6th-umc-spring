package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.MissionChallengingValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionChallengingValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface AlreadyChallenging {

    String message() default "이미 도전중인 미션입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
