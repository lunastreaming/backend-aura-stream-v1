package com.example.aurastream.repository;

import com.example.aurastream.model.entity.MainBannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MainBannerRepository extends JpaRepository<MainBannerEntity, Integer> {

    List<MainBannerEntity> findAllByOrderByTypeAscSortOrderAsc();

    Optional<MainBannerEntity> findFirstByTypeAndIsActiveTrue(String type);

    // CORREGIDO: Usando 'MainBannerEntity' en lugar de 'MainBanner'
    @Modifying
    @Query("UPDATE MainBannerEntity b SET b.isActive = false WHERE b.type = :type AND b.id != :exceptId")
    int deactivateOtherPopups(@Param("type") String type, @Param("exceptId") Integer exceptId);

    @Modifying
    @Query("UPDATE MainBannerEntity b SET b.isActive = false WHERE b.type = :type")
    int deactivateAllPopups(@Param("type") String type);

}
