package com.alt3rs.seorganiza.service;

import com.alt3rs.seorganiza.domain.activity.Activity;
import com.alt3rs.seorganiza.dto.ActivityRequestDTO;
import com.alt3rs.seorganiza.dto.ActivityResponseDTO;
import com.alt3rs.seorganiza.dto.mapper.ActivityMapper;
import com.alt3rs.seorganiza.exceptions.DatabaseException;
import com.alt3rs.seorganiza.exceptions.ResourceNotFoundException;
import com.alt3rs.seorganiza.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;

    public ActivityResponseDTO insertActivity(ActivityRequestDTO activityRequestDTO){
        // Converter DTO para entidade
        Activity activity = ActivityMapper.toEntity(activityRequestDTO);
        // Salvar a entidade no repositório
        Activity savedActivity = repository.save(activity);
        // Converter a entidade salva para DTO
        return ActivityMapper.toResponseDTO(savedActivity);
    }

    public void removeActivity(String id){
        try{
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public ActivityResponseDTO updateActivity(String id,  ActivityRequestDTO activityRequestDTO){
        // Verificar se a atividade existe
        Activity existingActivity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        // Criar uma nova instância da atividade com valores atualizados
        Activity updatedActivity = Activity.create(
                existingActivity.getId(),
                activityRequestDTO.date(),
                activityRequestDTO.description(),
                activityRequestDTO.value(),
                activityRequestDTO.typeTransaction(),
                activityRequestDTO.typeExpense()
        );

        // Salvar a atividade atualizada
        Activity savedActivity = repository.save(updatedActivity);

        // Converter a entidade atualizada para DTO
        return ActivityMapper.toResponseDTO(savedActivity);
    }

    public List<ActivityResponseDTO> listActivities(){
        return repository.findAll().stream()
                .map(ActivityMapper::toResponseDTO)
                .toList();
    }

    public Double calculateBalance(){
            return 0.1;
    }

}
