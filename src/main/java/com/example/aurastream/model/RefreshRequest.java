package com.example.aurastream.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshRequest {

    private String refreshToken;

}
