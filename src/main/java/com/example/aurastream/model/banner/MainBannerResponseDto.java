package com.example.aurastream.model.banner;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainBannerResponseDto {
    private Integer id;
    private String title;
    private String mediaUrl;
    private String mediaType;
    private String redirectUrl;
    private String type;
    private Boolean isActive;
    private Integer sortOrder;
}
