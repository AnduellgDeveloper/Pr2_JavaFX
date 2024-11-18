package co.edu.uniquindio.marketplace_fx.marketplace_app.model.session;

import co.edu.uniquindio.marketplace_fx.marketplace_app.model.User;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Session {
    private final String sessionId;
    private String username;
    private final LocalDateTime loginTime;
    private Map<String, Object> sessionData;


    public Session(String username) {
        this.sessionId = UUID.randomUUID().toString();
        this.username = username;
        this.loginTime = LocalDateTime.now();
        this.sessionData = new ConcurrentHashMap<>();
    }


    public String getSessionId() { return sessionId; }
    public String getUsername() { return username; }

    public void setSessionData(String key, Object value) {
        sessionData.put(key, value);
    }
    public Object getSessionData(String key) {
        return sessionData.get(key);
    }
    public LocalDateTime getLoginTime() {
        return loginTime;
    }
    public void setActive(boolean active) {
        active = true;
    }
}
