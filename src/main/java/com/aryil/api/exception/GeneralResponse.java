package com.aryil.api.exception;

import java.util.Map;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@Getter
@Setter
public class GeneralResponse<T> {

    private Map<String,String> errors;
    private T data;
    private String message;

    public GeneralResponse() {
    }

}