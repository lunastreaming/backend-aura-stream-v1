package com.example.aurastream.controller;

import com.example.aurastream.model.banner.MainBannerResponseDto;
import com.example.aurastream.service.MainBannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/banners-public")
@RequiredArgsConstructor
public class BannerPublicController {

    private final MainBannerService mainBannerService;

    @GetMapping("/active")
    public ResponseEntity<List<MainBannerResponseDto>> getActiveBanners(
            @RequestParam(value = "type", required = true) String type) {

        List<MainBannerResponseDto> activeBanners = mainBannerService.getActiveBannersByType(type);

        // Retorna HTTP 200 OK siempre con la lista filtrada (vacia o poblada)
        return ResponseEntity.ok(activeBanners);
    }

}
