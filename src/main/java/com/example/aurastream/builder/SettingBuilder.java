package com.example.aurastream.builder;

import com.example.aurastream.model.SettingEntity;
import com.example.aurastream.model.SettingResponse;
import org.springframework.stereotype.Component;

@Component
public class SettingBuilder {

    public SettingResponse toSettingResponse(SettingEntity settingEntity) {
        return SettingResponse
                .builder()
                .id(settingEntity.getId())
                .key(settingEntity.getKey())
                .type(settingEntity.getType())
                .valueText(settingEntity.getValueText())
                .valueNum(settingEntity.getValueNum())
                .valueBool(settingEntity.getValueBool())
                .description(settingEntity.getDescription())
                .updatedAt(settingEntity.getUpdatedAt())
                .updatedBy(settingEntity.getUpdatedBy() !=null ?
                        settingEntity.getUpdatedBy().getId() : null)
                .build();
    }

}
