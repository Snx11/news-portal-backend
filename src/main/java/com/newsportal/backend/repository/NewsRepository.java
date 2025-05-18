package com.newsportal.backend.repository;

import com.newsportal.backend.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findByCategory(String category);

    // SQLite uyumlu özel sorgu
    @Query(value = "SELECT * FROM news ORDER BY created_at DESC LIMIT 10", nativeQuery = true)
    List<News> getLatestNews();

    // Bu metodu kaldırıyoruz veya yorum satırına alıyoruz çünkü sorun yaratıyor
    // List<News> findTop10ByOrderByCreatedAtDesc();
}
