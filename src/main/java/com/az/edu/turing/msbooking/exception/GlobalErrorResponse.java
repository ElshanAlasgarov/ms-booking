package com.az.edu.turing.msbooking.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalErrorResponse {

    private UUID requestId;
    private String errorCode;
    private String errorMessage;
    private LocalDateTime localDateTime;

}
