package com.alt3rs.seorganiza.dto.mapper;

import com.alt3rs.seorganiza.domain.activity.Activity;
import com.alt3rs.seorganiza.domain.user.User;
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
                activity.getType(),
                activity.getUser() != null ? activity.getUser().getId() : null
        );
    }

    // Converte ActivityRequestDTO para Activity
    public static Activity toEntity(ActivityRequestDTO dto, User user) {
        if (dto == null) {
            return null;
        }
        return Activity.create(
                null, // ou qualquer valor padrão ou gerado automaticamente
                dto.date(),
                dto.description(),
                dto.value(),
                dto.type(),
                user // precisa passar o objeto User
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
                activity.getType(),
                activity.getUser() != null ? activity.getUser().getId() : null
        );
    }
}
