package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.validation.annotation.CheckPage;


@Component
@RequiredArgsConstructor
public class CheckPageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        int page = value - 1;

        if (page < -1) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_NOT_FOUND.toString()).addConstraintViolation();
        }

        return true;
    }
}
