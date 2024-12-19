package com.wealth.demo.util;

import com.wealth.demo.model.entity.User;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * SessionUtils 用於統一管理 HttpSession 中的用戶認證信息。
 */
@Component
public class SessionUtils {

    private static final Logger logger = LoggerFactory.getLogger(SessionUtils.class);

    // 常量定義
    private static final String CURRENT_USER_KEY = "currentUser";
    private static final String CURRENT_USER_ID_KEY = "currentUserId";

    /**
     * 設置認證用戶到 Session。
     *
     * @param session HttpSession
     * @param user    已認證的 User 對象
     */
    public void setAuthenticatedUser(HttpSession session, User user) {
        if (session == null) {
            throw new IllegalArgumentException("Session 不可為 null");
        }
        session.setAttribute(CURRENT_USER_KEY, user.getUsername());
        session.setAttribute(CURRENT_USER_ID_KEY, user.getId());
        logger.info("設置用戶到 Session: username={}, userId={}", user.getUsername(), user.getId());
    }

    /**
     * 獲取當前認證用戶名。
     *
     * @param session HttpSession
     * @return 當前用戶名的 Optional
     */
    public Optional<String> getAuthenticatedUsername(HttpSession session) {
        if (session == null) {
            logger.warn("Session 不存在");
            return Optional.empty();
        }
        return Optional.ofNullable((String) session.getAttribute(CURRENT_USER_KEY));
    }

    /**
     * 獲取當前認證用戶 ID。
     *
     * @param session HttpSession
     * @return 當前用戶 ID 的 Optional
     */
    public Optional<Long> getAuthenticatedUserId(HttpSession session) {
        if (session == null) {
            logger.warn("Session 不存在");
            return Optional.empty();
        }
        return Optional.ofNullable((Long) session.getAttribute(CURRENT_USER_ID_KEY));
    }

    /**
     * 清除當前 Session。
     *
     * @param session HttpSession
     */
    public void clearSession(HttpSession session) {
        if (session == null) {
            throw new IllegalArgumentException("Session 不可為 null");
        }
        logger.info("清除 Session: {}", session.getId());
        session.invalidate();
    }
}
