package com.twentyeightstone.graphdemo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ApplicationException extends RuntimeException {

    @Getter
    String message;
}
