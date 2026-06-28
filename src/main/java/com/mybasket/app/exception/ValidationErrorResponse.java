package com.mybasket.app.exception;

public record ValidationErrorResponse(
        String filed,
        String message

) {
}
