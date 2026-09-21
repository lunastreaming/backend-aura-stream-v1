package com.example.aurastream.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminDepositRequest {

    private UUID userId;
    private BigDecimal amount;
    private String note;
}
