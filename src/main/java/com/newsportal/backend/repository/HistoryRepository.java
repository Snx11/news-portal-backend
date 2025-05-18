package com.newsportal.backend.repository;

import com.newsportal.backend.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {

    // Native SQL sorguları kullanarak metot adı çözümleme sorunlarını önleyelim
    @Query(value = "SELECT * FROM history WHERE user_id = :userId ORDER BY viewed_at DESC", nativeQuery = true)
    List<History> findByUserIdOrderByViewedAtDesc(@Param("userId") String userId);

    @Query(value = "SELECT * FROM history ORDER BY viewed_at DESC", nativeQuery = true)
    List<History> findAllByOrderByViewedAtDesc();

    @Query(value = "SELECT COUNT(*) > 0 FROM history WHERE news_id = :newsId AND user_id = :userId", nativeQuery = true)
    boolean existsByNewsIdAndUserId(@Param("newsId") Integer newsId, @Param("userId") String userId);

    @Query(value = "DELETE FROM history WHERE user_id = :userId", nativeQuery = true)
    void deleteByUserId(@Param("userId") String userId);
}
