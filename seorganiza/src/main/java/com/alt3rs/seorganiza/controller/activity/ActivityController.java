package com.alt3rs.seorganiza.controller.activity;

import com.alt3rs.seorganiza.dto.ActivityRequestDTO;
import com.alt3rs.seorganiza.dto.ActivityResponseDTO;
import com.alt3rs.seorganiza.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponseDTO> createActivity(@RequestBody ActivityRequestDTO activityRequestDTO) {
        ActivityResponseDTO response = activityService.insertActivity(activityRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivityResponseDTO> updateActivity(
            @PathVariable String id,
            @RequestBody ActivityRequestDTO activityRequestDTO) {
        ActivityResponseDTO response = activityService.updateActivity(id, activityRequestDTO);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable String id) {
        activityService.removeActivity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponseDTO>> listActivities() {
        List<ActivityResponseDTO> activities = activityService.listActivities();
        return ResponseEntity.ok().body(activities);
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> calculateBalance() {
        Double balance = activityService.calculateBalance();
        return ResponseEntity.ok().body(balance);
    }
}
