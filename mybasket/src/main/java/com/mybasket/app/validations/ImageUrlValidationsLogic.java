package com.mybasket.app.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImageUrlValidationsLogic implements ConstraintValidator<ValidImageUrl,String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //logic
        if(value.toLowerCase().endsWith(".png") || value.toLowerCase().endsWith(".jpg"))
            return true;
        else return false;

    }
}
