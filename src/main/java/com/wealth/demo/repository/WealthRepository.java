package com.wealth.demo.repository;

import com.wealth.demo.model.entity.Wealth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WealthRepository extends JpaRepository<Wealth, Long> {
    List<Wealth> findAllByUserId(Long userId); // 查詢某用戶的所有收支記錄
    List<Wealth> findAllByUserIdAndCategoryId(Long userId, Long categoryId); // 查詢某用戶特定分類的收支記錄
}
