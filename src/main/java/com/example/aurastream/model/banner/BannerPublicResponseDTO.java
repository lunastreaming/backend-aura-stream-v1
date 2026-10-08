package com.example.aurastream.model.banner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BannerPublicResponseDTO {

    private String mediaUrl;
    private String mediaType;
    private String redirectUrl;

}