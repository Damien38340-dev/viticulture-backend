package com.viticulture.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthCheckController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public ResponseEntity<Map<String, String>> healthCheck() {
        try {
            // Simple requête pour tester la connexion de la base de données
            jdbcTemplate.queryForObject("SELECT 1 ", Integer.class);
            return ResponseEntity.ok(Map.of(
                    "status", "ok",
                    "database", "connected"));
        } catch (Exception e) {
            return ResponseEntity.status(503).body(Map.of(
                    "status", "error",
                    "message", "Database connection failed: " + e.getMessage(),
                    "database", "disconnected"));
        }
    }
}
