package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Session {
    private final String sessionId;
    private final User user;
    private final LocalDateTime loginTime;
    private Map<String, Object> sessionData;
    private boolean isActive;

    public Session(User user) {
        this.sessionId = UUID.randomUUID().toString();
        this.user = user;
        this.loginTime = LocalDateTime.now();
        this.sessionData = new ConcurrentHashMap<>();
        this.isActive = true;
    }

    public void setSessionData(String key, Object value) {
        sessionData.put(key, value);
    }
    public Object getSessionData(String key) {
        return sessionData.get(key);
    }
    public boolean isActive() {
        return true;
    }
    public String getSessionId() {
        return sessionId;
    }
    public User getUser() {
        return user;
    }
    public LocalDateTime getLoginTime() {
        return loginTime;
    }
    public Map<String, Object> getSessionData() {
        return sessionData;
    }
    public void setSessionData(Map<String, Object> sessionData) {
        this.sessionData = sessionData;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
}
