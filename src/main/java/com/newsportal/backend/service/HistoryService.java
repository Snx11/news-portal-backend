package com.newsportal.backend.service;

import com.newsportal.backend.entity.History;
import com.newsportal.backend.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<History> getUserHistory(String userId) {
        // If userId is provided, get history for that user
        if (userId != null && !userId.isEmpty()) {
            return historyRepository.findByUserIdOrderByViewedAtDesc(userId);
        }

        // Otherwise, get all history (for now, before implementing authentication)
        return historyRepository.findAllByOrderByViewedAtDesc();
    }

    @Transactional
    public History addToHistory(Integer newsId, String title, String userId) {
        // For now, use a default userId if none is provided
        String effectiveUserId = (userId != null && !userId.isEmpty()) ? userId : "anonymous";

        // Check if this news item is already in history
        boolean exists = historyRepository.existsByNewsIdAndUserId(newsId, effectiveUserId);

        // If it exists, we could update the timestamp, but for simplicity, we'll just return
        if (exists) {
            // Find and update the existing entry
            List<History> histories = historyRepository.findByUserIdOrderByViewedAtDesc(effectiveUserId);
            for (History h : histories) {
                if (h.getNewsId().equals(newsId)) {
                    h.setViewedAt(LocalDateTime.now());
                    return historyRepository.save(h);
                }
            }
        }

        // Create a new history entry
        History history = new History();
        history.setNewsId(newsId);
        history.setTitle(title);
        history.setUserId(effectiveUserId);
        history.setViewedAt(LocalDateTime.now());

        return historyRepository.save(history);
    }

    @Transactional
    public void clearHistory(String userId) {
        // For now, use a default userId if none is provided
        String effectiveUserId = (userId != null && !userId.isEmpty()) ? userId : "anonymous";

        historyRepository.deleteByUserId(effectiveUserId);
    }
}