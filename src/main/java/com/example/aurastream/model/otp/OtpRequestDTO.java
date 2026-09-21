package com.example.aurastream.model.otp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OtpRequestDTO(
        @NotBlank(message = "El teléfono es obligatorio")
        String telefono,

        @NotNull(message = "El contexto de la solicitud es obligatorio")
        OtpContext contexto // Se mapeará automáticamente desde el JSON (ej: "REGISTER_PROVIDER")
) {
}
