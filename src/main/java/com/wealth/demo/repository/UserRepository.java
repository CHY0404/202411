package com.wealth.demo.repository;

import com.wealth.demo.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // 根據用戶名查找用戶
    boolean existsByUsername(String username); // 檢查用戶名是否已存在

    // 新增 email 檢查方法
    Optional<User> findByEmail(String email); // 根據 email 查找用戶
    boolean existsByEmail(String email); // 檢查 email 是否已存在
}
