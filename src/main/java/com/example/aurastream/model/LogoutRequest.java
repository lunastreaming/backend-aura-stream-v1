package com.example.aurastream.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequest {

    private String accessToken;
    private String refreshToken;
    // getters y setters

}
