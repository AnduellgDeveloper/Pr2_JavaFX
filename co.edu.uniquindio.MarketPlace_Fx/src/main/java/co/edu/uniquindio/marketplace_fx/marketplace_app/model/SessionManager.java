package co.edu.uniquindio.marketplace_fx.marketplace_app.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class SessionManager {
    private static SessionManager instance;
    private Map<String, Session> activeSessions;
    private SharedData sharedData;
    private final ExecutorService notificationExecutor;


    private SessionManager() {
        activeSessions = new ConcurrentHashMap<>();
        sharedData = new SharedData();
        notificationExecutor = Executors.newSingleThreadExecutor();
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
    public Session createSession(User user) {
        Session session = new Session(user);
        activeSessions.put(session.getSessionId(), session);
        return session;
    }
    // Obtener una sesión existente
    public Session getSession(String sessionId) {
        return activeSessions.get(sessionId);
    }
    // Cerrar una sesión específica
    public void closeSession(String sessionId) {
        Session session = activeSessions.remove(sessionId);
        if (session != null) {
            session.setActive(false);
        }
    }
    // Compartir datos entre sesiones
    public void shareData(String key, Object value) {
        sharedData.addSharedObject(key, value);
        notifyAllSessions("Nuevo dato compartido: " + key);
    }
    // Obtener datos compartidos
    public Object getSharedData(String key) {
        return sharedData.getSharedObject(key);
    }
    // Enviar notificación a todas las sesiones activas
    private void notifyAllSessions(String message) {
        notificationExecutor.execute(() -> {
            sharedData.addNotification(message);
            for (Session session : activeSessions.values()) {
                if (session.isActive()) {
                    // Aquí podrías implementar un sistema de observers
                    // para notificar a cada sesión
                }
            }
        });
    }
    // Obtener todas las sesiones activas
    public List<Session> getActiveSessions() {
        return new ArrayList<>(activeSessions.values());
    }
}
