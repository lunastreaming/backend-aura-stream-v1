package com.example.aurastream.model.banner;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BannerResponseDTO {

    private Integer id;
    private String title;
    private String mediaUrl;
    private String mediaType;
    private String redirectUrl;
    private String type;
    private Boolean isActive;
    private Integer sortOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID updatedBy;

}

