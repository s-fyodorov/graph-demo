package com.twentyeightstone.graphdemo.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ErrorResponse {

    @Getter
    private final String errorMessage;
}
