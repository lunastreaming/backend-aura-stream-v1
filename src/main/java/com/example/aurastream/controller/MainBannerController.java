package com.example.aurastream.controller;

import com.example.aurastream.model.banner.BannerPublicResponseDTO;
import com.example.aurastream.model.banner.BannerRequestDTO;
import com.example.aurastream.model.banner.BannerResponseDTO;
import com.example.aurastream.service.MainBannerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainBannerController {

    private final MainBannerService bannerService;

    // ==========================================
    // RUTA PÚBLICA (Consumida por la Web Principal)
    // ==========================================

    @GetMapping("/public/banners/popup")
    public ResponseEntity<BannerPublicResponseDTO> getActivePopup() {
        return bannerService.getActivePopup()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
        // Devuelve 244 No Content si el administrador apagó el popup
    }

    // ==========================================
    // RUTAS DE ADMINISTRACIÓN (CRUD + STATUS)
    // ==========================================

    @GetMapping("/admin/banners")
    public ResponseEntity<List<BannerResponseDTO>> getAllBanners() {
        return ResponseEntity.ok(bannerService.getAllBanners());
    }

    @PostMapping("/admin/banners")
    public ResponseEntity<BannerResponseDTO> createBanner(
            @Valid @RequestBody BannerRequestDTO request,
            Principal userPrincipal) { // Extraído previamente de tu filtro JWT

        BannerResponseDTO response = bannerService.createBanner(request, UUID.fromString(userPrincipal.getName()));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/admin/banners/{id}")
    public ResponseEntity<BannerResponseDTO> updateBanner(
            @PathVariable Integer id,
            @Valid @RequestBody BannerRequestDTO request,
            Principal userPrincipal) {

        return ResponseEntity.ok(bannerService.updateBanner(id, request, UUID.fromString(userPrincipal.getName())));
    }

    @DeleteMapping("/admin/banners/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable Integer id) {
        bannerService.deleteBanner(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint recomendado para activar o desactivar el Popup o elementos del carrusel.
     * Consumo: PATCH /api/admin/banners/5/status?active=true
     */
    @PatchMapping("/admin/banners/{id}/status")
    public ResponseEntity<BannerResponseDTO> toggleBannerStatus(
            @PathVariable Integer id,
            @RequestParam("active") Boolean active,
            Principal userPrincipal) {

        return ResponseEntity.ok(bannerService.toggleStatus(id, active, UUID.fromString(userPrincipal.getName())));
    }

}
