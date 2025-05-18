package com.newsportal.backend.controller;

import com.newsportal.backend.entity.History;
import com.newsportal.backend.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/history")
public class HistoryController {

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping
    public ResponseEntity<List<History>> getUserHistory(
            @RequestParam(required = false) String userId) {
        try {
            List<History> history = historyService.getUserHistory(userId);
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<History> addToHistory(@RequestBody Map<String, Object> payload) {
        try {
            Integer newsId = Integer.valueOf(payload.get("newsId").toString());
            String title = (String) payload.get("title");
            String userId = payload.get("userId") != null ? (String) payload.get("userId") : "anonymous";

            History history = historyService.addToHistory(newsId, title, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(history);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> clearHistory(@RequestParam(required = false) String userId) {
        try {
            historyService.clearHistory(userId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}