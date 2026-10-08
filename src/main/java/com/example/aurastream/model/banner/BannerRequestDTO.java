package com.example.aurastream.model.banner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BannerRequestDTO {

    private String title;

    @NotBlank(message = "La URL del recurso multimedia es obligatoria")
    private String mediaUrl;

    @Pattern(regexp = "image|video", message = "El tipo de multimedia debe ser 'image' o 'video'")
    private String mediaType;

    private String redirectUrl;

    @NotBlank(message = "El tipo de banner es obligatorio")
    @Pattern(regexp = "popup|carousel", message = "El tipo debe ser 'popup' o 'carousel'")
    private String type;

    private Integer sortOrder;
}
