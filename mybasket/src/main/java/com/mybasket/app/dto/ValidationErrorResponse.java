package com.mybasket.app.dto;

public record ValidationErrorResponse(
        String filed,
        String message

) {
}
