package com.alt3rs.seorganiza.dto.mapper;

import com.alt3rs.seorganiza.domain.activity.Activity;
import com.alt3rs.seorganiza.dto.ActivityRequestDTO;
import com.alt3rs.seorganiza.dto.ActivityResponseDTO;

public class ActivityMapper {

    // Converte Activity para ActivityRequestDTO
    public static ActivityRequestDTO toDTO(Activity activity) {
        if (activity == null) {
            return null;
        }
        return new ActivityRequestDTO(
                activity.getDate(),
                activity.getDescription(),
                activity.getValue(),
                activity.getTypeTransaction(),
                activity.getTypeExpense()
        );
    }

    // Converte ActivityRequestDTO para Activity
    public static Activity toEntity(ActivityRequestDTO dto) {
        if (dto == null) {
            return null;
        }
        return Activity.create(
                null, // ou qualquer valor padrão ou gerado automaticamente
                dto.date(),
                dto.description(),
                dto.value(),
                dto.typeTransaction(),
                dto.typeExpense()
        );
    }

    // Converte Activity para ActivityResponseDTO
    public static ActivityResponseDTO toResponseDTO(Activity activity) {
        if (activity == null) {
            return null;
        }
        return new ActivityResponseDTO(
                activity.getId(),
                activity.getDate(),
                activity.getDescription(),
                activity.getValue(),
                activity.getTypeTransaction(),
                activity.getTypeExpense()
        );
    }
}
