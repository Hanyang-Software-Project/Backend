package com.ziggs.ziggs_backend.controller;

import com.ziggs.ziggs_backend.entity.Alert;
import com.ziggs.ziggs_backend.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/alerts")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping
    public ResponseEntity<String> createAlert(@RequestBody Map<String, String> payload) {
        String filePath = payload.get("filePath");

        if (filePath == null || filePath.isEmpty()) {
            return ResponseEntity.badRequest().body("File path is required.");
        }

        try {
            Alert alert = alertService.createAlert(filePath);
            return new ResponseEntity<>(String.format("Alert created successfully with file path: %s", filePath), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("SoundData not found for the given file path.");
        }
    }

    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertService.getAllAlerts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alert> getAlertById(@PathVariable Long id) {
        Optional<Alert> alert = alertService.getAlertById(id);
        return alert.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PutMapping("/{id}")
    public ResponseEntity<Alert> updateAlert(@PathVariable Long id, @RequestBody Map<String, Boolean> payload) {
        Boolean threatFlag = payload.get("threatFlag");

        if (threatFlag == null) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            Alert updatedAlert = alertService.updateThreatFlag(id, threatFlag);
            return ResponseEntity.ok(updatedAlert);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Long id) {
        alertService.deleteAlert(id);
        return ResponseEntity.noContent().build();
    }

}
