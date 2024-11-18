package co.edu.uniquindio.marketplace_fx.marketplace_app.model.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private static SessionManager instance;
    private Map<String, Session> activeSessions;
    private SharedData sharedData;

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
    // Crear una nueva sesión
    public Session createSession(String username) {
        Session session = new Session(username);
        activeSessions.put(session.getSessionId(), session);
        return session;
    }
    // Cerrar una sesión específica
    public void closeSession(String sessionId) {
        Session session = activeSessions.remove(sessionId);
        if (session != null) {
            session.setActive(false);
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
