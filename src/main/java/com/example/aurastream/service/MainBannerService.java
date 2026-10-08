package com.example.aurastream.service;

import com.example.aurastream.model.banner.BannerPublicResponseDTO;
import com.example.aurastream.model.banner.BannerRequestDTO;
import com.example.aurastream.model.banner.BannerResponseDTO;
import com.example.aurastream.model.banner.MainBannerResponseDto;
import com.example.aurastream.model.entity.MainBannerEntity;
import com.example.aurastream.repository.MainBannerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainBannerService {

    private final MainBannerRepository bannerRepository;

    // --- MÉTODOS PARA EL ADMINISTRADOR (CRUD) ---

    @Transactional(readOnly = true)
    public List<BannerResponseDTO> getAllBanners() {
        return bannerRepository.findAllByOrderByTypeAscSortOrderAsc().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), List::copyOf));
    }

    @Transactional
    public BannerResponseDTO createBanner(BannerRequestDTO dto, UUID adminId) {
        MainBannerEntity banner = MainBannerEntity.builder()
                .title(dto.getTitle())
                .mediaUrl(dto.getMediaUrl())
                .mediaType(dto.getMediaType())
                .redirectUrl(dto.getRedirectUrl())
                .type(dto.getType())
                .isActive(false) // Todo banner nuevo entra desactivado por seguridad
                .sortOrder(dto.getSortOrder())
                .updatedBy(adminId)
                .build();

        return convertToResponseDto(bannerRepository.save(banner));
    }

    @Transactional
    public BannerResponseDTO updateBanner(Integer id, BannerRequestDTO dto, UUID adminId) {
        MainBannerEntity banner = bannerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Banner no encontrado con ID: " + id));

        banner.setTitle(dto.getTitle());
        banner.setMediaUrl(dto.getMediaUrl());
        banner.setMediaType(dto.getMediaType());
        banner.setRedirectUrl(dto.getRedirectUrl());
        banner.setSortOrder(dto.getSortOrder());
        banner.setUpdatedBy(adminId);

        return convertToResponseDto(bannerRepository.save(banner));
    }

    @Transactional
    public void deleteBanner(Integer id) {
        if (!bannerRepository.existsById(id)) {
            throw new EntityNotFoundException("Banner no encontrado");
        }
        bannerRepository.deleteById(id);
    }

    /**
     * Endpoint especializado para activar o desactivar el Flag de manera controlada.
     */
    @Transactional
    public BannerResponseDTO toggleStatus(Integer id, Boolean status, UUID adminId) {
        MainBannerEntity banner = bannerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Banner no encontrado"));

        banner.setIsActive(status);
        banner.setUpdatedBy(adminId);

        // REGLA DE ORO: Si es un POPUP y lo están ACTIVANDO, desactivamos los demás.
        if (Boolean.TRUE.equals(status) && "popup".equalsIgnoreCase(banner.getType())) {
            // Cambiar esta línea para pasarle "popup" explícitamente
            bannerRepository.deactivateOtherPopups("popup", id);
        }

        return convertToResponseDto(bannerRepository.save(banner));
    }


    // --- MÉTODOS PARA EL CLIENTE PÚBLICO (FRONTEND HOME) ---

    @Transactional(readOnly = true)
    public Optional<BannerPublicResponseDTO> getActivePopup() {
        return bannerRepository.findFirstByTypeAndIsActiveTrue("popup")
                .map(b -> new BannerPublicResponseDTO(b.getMediaUrl(), b.getMediaType(), b.getRedirectUrl()));
    }


    // --- HELPER DE MAPEO MANUAL ---
    private BannerResponseDTO convertToResponseDto(MainBannerEntity banner) {
        BannerResponseDTO response = new BannerResponseDTO();
        response.setId(banner.getId());
        response.setTitle(banner.getTitle());
        response.setMediaUrl(banner.getMediaUrl());
        response.setMediaType(banner.getMediaType());
        response.setRedirectUrl(banner.getRedirectUrl());
        response.setType(banner.getType());
        response.setIsActive(banner.getIsActive());
        response.setSortOrder(banner.getSortOrder());
        response.setCreatedAt(banner.getCreatedAt());
        response.setUpdatedAt(banner.getUpdatedAt());
        response.setUpdatedBy(banner.getUpdatedBy());
        return response;
    }

    @Transactional(readOnly = true)
    public List<MainBannerResponseDto> getActiveBannersByType(String type) {
        if (type == null || type.trim().isEmpty()) {
            return Collections.emptyList();
        }

        String normalizedType = type.trim().toLowerCase(); // Usamos minúsculas: 'popup' o 'carousel'

        // CASO 1: Es un POPUP (Solo puede haber uno activo)
        if ("popup".equals(normalizedType)) {
            return bannerRepository.findFirstByTypeAndIsActiveTrue("popup")
                    .map(entity -> {
                        List<MainBannerResponseDto> list = new ArrayList<>();
                        list.add(mapToDto(entity));
                        return list;
                    })
                    .orElse(Collections.emptyList()); // Si no hay popup activo, retorna []
        }

        // CASO 2: Es un CAROUSEL (Pueden haber múltiples banners activos ordenados)
        // Nota: Si en el futuro agregas un método findAllByTypeAndIsActiveTrueOrderBySortOrderAsc en tu repo,
        // lo ideal sería usarlo aquí. Por ahora, filtramos el ordenamiento general de tu repo:
        if ("carousel".equals(normalizedType)) {
            return bannerRepository.findAllByOrderByTypeAscSortOrderAsc()
                    .stream()
                    .filter(b -> "carousel".equals(b.getType()) && Boolean.TRUE.equals(b.getIsActive()))
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    // Método utilitario privado para mapear la Entidad al DTO
    private MainBannerResponseDto mapToDto(MainBannerEntity entity) {
        return MainBannerResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .mediaUrl(entity.getMediaUrl())
                .mediaType(entity.getMediaType())
                .redirectUrl(entity.getRedirectUrl())
                .type(entity.getType())
                .isActive(entity.getIsActive())
                .sortOrder(entity.getSortOrder())
                .build();
    }

}

