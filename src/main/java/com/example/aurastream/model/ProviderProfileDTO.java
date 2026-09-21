package com.example.aurastream.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProviderProfileDTO {

    private UUID id;
    private UUID userId;
    private Boolean canTransfer;

}
