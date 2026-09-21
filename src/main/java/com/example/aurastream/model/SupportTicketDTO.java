package com.example.aurastream.model;


import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupportTicketDTO {

    private Long id;
    private Long stockId;
    private String issueType;
    private String description;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant resolvedAt;
    private String resolutionNote;

    // 🆕 Campos calculados
    private Long openTimeInSeconds; // Para cálculos exactos o formateo personalizado en UI
    private String formattedOpenTime; // Ej: "2 días 4 horas"

}
