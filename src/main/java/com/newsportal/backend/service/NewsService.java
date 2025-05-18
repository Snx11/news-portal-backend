package com.newsportal.backend.service;

import com.newsportal.backend.entity.News;
import com.newsportal.backend.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> getLatestNews() {
        // Değiştirildi: findTop10ByOrderByCreatedAtDesc() yerine getLatestNews() kullanılıyor
        return newsRepository.getLatestNews();
    }

    public List<News> getNewsByCategory(String category) {
        return newsRepository.findByCategory(category);
    }

    public Optional<News> getNewsById(Long id) {
        return newsRepository.findById(id);
    }

    public News saveNews(News news) {
        return newsRepository.save(news);
    }

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public void saveAllNews(List<News> newsList) {
        newsRepository.saveAll(newsList);
    }
}
