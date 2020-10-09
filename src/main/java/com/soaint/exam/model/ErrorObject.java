package com.soaint.exam.model;

import lombok.*;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ErrorObject {

    private String httpStatus;
    private String message;
    private int code;
    private String backendMessage;


}
