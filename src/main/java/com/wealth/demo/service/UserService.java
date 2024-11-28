package com.wealth.demo.service;

import com.wealth.demo.model.entity.User;
import com.wealth.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    // 日誌記錄器
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 創建新用戶
     * @param user 新用戶實體
     * @return 保存後的用戶實體
     */
    @Transactional
    public User createUser(User user) {
        logger.info("正在創建新用戶: {}", user.getUsername());

        // 檢查用戶名是否已存在
        if (userRepository.existsByUsername(user.getUsername())) {
            logger.warn("用戶名 {} 已存在，無法創建用戶！", user.getUsername());
            throw new IllegalArgumentException("用戶名已存在！");
        }

        User savedUser = userRepository.save(user);
        logger.info("新用戶創建成功: {}", savedUser.getUsername());
        return savedUser;
    }

    /**
     * 獲取用戶資訊
     * @param id 用戶 ID
     * @return 用戶實體（若找到）
     */
    public Optional<User> getUserById(Long id) {
        logger.info("正在查詢用戶 ID: {}", id);
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            logger.info("用戶查詢成功: {}", user.get().getUsername());
        } else {
            logger.warn("未找到用戶 ID: {}", id);
        }

        return user;
    }

    /**
     * 更新用戶資訊
     * @param id 用戶 ID
     * @param updatedUser 更新後的用戶實體
     * @return 更新後的用戶實體
     */
    @Transactional
    public User updateUser(Long id, User updatedUser) {
        logger.info("正在更新用戶 ID: {}", id);

        User existingUser = userRepository.findById(id).orElseThrow(() -> {
            logger.error("用戶 ID {} 不存在，無法更新！", id);
            return new IllegalArgumentException("用戶不存在！");
        });

        // 更新資料
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setEmail(updatedUser.getEmail());

        User savedUser = userRepository.save(existingUser);
        logger.info("用戶 ID: {} 更新成功！", savedUser.getId());
        return savedUser;
    }

    /**
     * 刪除用戶
     * @param id 用戶 ID
     */
    @Transactional
    public void deleteUser(Long id) {
        logger.info("正在刪除用戶 ID: {}", id);

        if (!userRepository.existsById(id)) {
            logger.warn("用戶 ID: {} 不存在，無法刪除！", id);
            throw new IllegalArgumentException("用戶不存在！");
        }

        userRepository.deleteById(id);
        logger.info("用戶 ID: {} 已成功刪除。", id);
    }

    /**
     * 用戶登錄驗證
     * @param username 用戶名
     * @param password 密碼
     * @return 驗證成功的用戶
     */
    public User validateUser(String username, String password) {
        logger.info("正在驗證用戶登錄，使用者名稱: {}", username);

        User user = userRepository.findByUsername(username).orElseThrow(() -> {
            logger.warn("用戶名 {} 不存在！", username);
            return new IllegalArgumentException("用戶不存在！");
        });

        if (!user.getPassword().equals(password)) {
            logger.warn("用戶名 {} 的密碼不正確！", username);
            throw new IllegalArgumentException("密碼錯誤！");
        }

        logger.info("用戶名 {} 登錄成功！", username);
        return user;
    }
}
