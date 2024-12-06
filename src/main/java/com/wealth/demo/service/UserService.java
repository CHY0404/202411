package com.wealth.demo.service;

import com.wealth.demo.exception.EmailAlreadyExistsException;
import com.wealth.demo.exception.UserAlreadyExistsException;
import com.wealth.demo.exception.UsernameNotFoundException;
import com.wealth.demo.exception.PasswordMismatchException;
import com.wealth.demo.model.dto.UserLoginDTO;
import com.wealth.demo.model.dto.UserRegisterDTO;
import com.wealth.demo.model.entity.User;
import com.wealth.demo.repository.UserRepository;
import com.wealth.demo.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 註冊新用戶
     *
     * @param userRegisterDTO 用戶註冊信息
     */
    @Transactional
    public void register(UserRegisterDTO userRegisterDTO) {
        logger.info("開始註冊新用戶: {}", userRegisterDTO.getUsername());

        // 檢查用戶名是否已存在
        if (userRepository.existsByUsername(userRegisterDTO.getUsername())) {
            logger.warn("用戶名已存在: {}", userRegisterDTO.getUsername());
            throw new UserAlreadyExistsException("用戶名已存在！");
        }

        // 檢查電子郵件是否已存在
        if (userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            logger.warn("電子郵件已存在: {}", userRegisterDTO.getEmail());
            throw new EmailAlreadyExistsException("電子郵件已存在！");
        }

        // 創建新用戶
        User newUser = new User();
        newUser.setUsername(userRegisterDTO.getUsername());
        newUser.setEmail(userRegisterDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword())); // 密碼加密

        userRepository.save(newUser);
        logger.info("用戶 {} 註冊成功！", userRegisterDTO.getUsername());
    }

    /**
     * 用戶登入
     *
     * @param userLoginDTO 用戶登入信息
     * @return JWT Token
     */
    @Transactional
    public String login(UserLoginDTO userLoginDTO) {
        logger.info("開始驗證用戶登錄: {}", userLoginDTO.getUsername());

        // 查詢用戶
        User user = userRepository.findByUsername(userLoginDTO.getUsername())
                .orElseThrow(() -> {
                    logger.warn("用戶名不存在: {}", userLoginDTO.getUsername());
                    return new UsernameNotFoundException("用戶名不存在！");
                });

        // 驗證密碼
        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            logger.warn("密碼錯誤，用戶: {}", userLoginDTO.getUsername());
            throw new PasswordMismatchException("密碼錯誤！");
        }

        // 生成 JWT Token
        String token = jwtUtil.generateToken(user.getUsername());
        logger.info("用戶 {} 登錄成功，JWT Token: {}", user.getUsername(), token);
        return token;
    }

    /**
     * 根據 ID 查詢用戶
     *
     * @param id 用戶 ID
     * @return 用戶實體
     */
    public Optional<User> getUserById(Long id) {
        logger.info("查詢用戶 ID: {}", id);

        return userRepository.findById(id).map(user -> {
            logger.info("用戶查詢成功: {}", user.getUsername());
            return user;
        });
    }

    /**
     * 更新用戶信息
     *
     * @param id          用戶 ID
     * @param updatedUser 更新後的用戶信息
     * @return 更新後的用戶實體
     */
    @Transactional
    public User updateUser(Long id, User updatedUser) {
        logger.info("更新用戶 ID: {}", id);

        User existingUser = userRepository.findById(id).orElseThrow(() -> {
            logger.warn("用戶 ID 不存在: {}", id);
            return new IllegalArgumentException("用戶不存在！");
        });

        // 更新用戶屬性
        if (updatedUser.getUsername() != null) {
            existingUser.setUsername(updatedUser.getUsername());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        userRepository.save(existingUser);
        logger.info("用戶 ID: {} 更新成功！", id);
        return existingUser;
    }

    /**
     * 刪除用戶
     *
     * @param id 用戶 ID
     */
    @Transactional
    public void deleteUser(Long id) {
        logger.info("刪除用戶 ID: {}", id);

        if (!userRepository.existsById(id)) {
            logger.warn("用戶 ID 不存在: {}", id);
            throw new IllegalArgumentException("用戶不存在！");
        }

        userRepository.deleteById(id);
        logger.info("用戶 ID: {} 已刪除。", id);
    }
}
