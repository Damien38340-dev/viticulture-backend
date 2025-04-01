package com.viticulture.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/db")
    public Map<String, String> testDb() {
        try (Connection conn = dataSource.getConnection()) {
            return Map.of("status", "success", "message", "Connexion à la base de données réussie");
        } catch (Exception e) {
            return Map.of("status", "error", "message", e.getMessage());
        }
    }
}