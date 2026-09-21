package com.example.aurastream.controller;

import com.example.aurastream.model.SettingRequest;
import com.example.aurastream.model.SettingResponse;
import com.example.aurastream.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/settings")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;

    @GetMapping
    public ResponseEntity<List<SettingResponse>> getSetting() {
        return ResponseEntity.ok(settingService.getSettings());
    }

    @PatchMapping("/{key}")
    public ResponseEntity<SettingResponse> patch(@PathVariable String key,
                                                 @RequestBody SettingRequest request,
                                                 Principal principal) {
        UUID adminId = UUID.fromString(principal.getName());
        // Pasamos el request completo
        SettingResponse settingResponse = settingService.updateSetting(key, request, adminId);
        return ResponseEntity.ok(settingResponse);
    }

    @GetMapping("/save")
    public void saveSetting() {
        settingService.saveSetting();
    }

}
