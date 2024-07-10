package umc.spring.apiPayload.exception.handler;

import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.exception.GeneralException;

public class FoodCatregoryHandler extends GeneralException {
    public FoodCatregoryHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
