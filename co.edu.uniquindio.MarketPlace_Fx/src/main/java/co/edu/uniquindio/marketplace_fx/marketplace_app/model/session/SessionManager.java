package co.edu.uniquindio.marketplace_fx.marketplace_app.model.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class SessionManager {
    private static SessionManager instance;
    private Map<String, Session> activeSessions;
    private SharedData sharedData;
    private Consumer<Session> onSessionCreatedCallback;
    private Consumer<Session> onSessionClosedCallback;

    private SessionManager() {
        activeSessions = new ConcurrentHashMap<>();
        sharedData = new SharedData();
    }
    public static SessionManager getInstance() {
        if (instance == null) {
            synchronized (SessionManager.class) {
                if (instance == null) {
                    instance = new SessionManager();
                }
            }
        }
        return instance;
    }
    public void setOnSessionCreatedCallback(Consumer<Session> callback) {
        this.onSessionCreatedCallback = callback;
    }

    public void setOnSessionClosedCallback(Consumer<Session> callback) {
        this.onSessionClosedCallback = callback;
    }

    public Session createSession(String username) {
        Session session = new Session(username);
        activeSessions.put(session.getSessionId(), session);
        if (onSessionCreatedCallback != null) {
            onSessionCreatedCallback.accept(session);
        }
        return session;
    }

    public void closeSession(String sessionId) {
        Session session = activeSessions.remove(sessionId);
        if (session != null) {
            session.setActive(false);
            if (onSessionClosedCallback != null) {
                onSessionClosedCallback.accept(session);
            }
        }
    }

    // Obtener datos compartidos
    public SharedData getSharedData() {
        return sharedData;
    }
    // Obtener todas las sesiones activas
    public List<Session> getActiveSessions() {
        return new ArrayList<>(activeSessions.values());
    }
}
