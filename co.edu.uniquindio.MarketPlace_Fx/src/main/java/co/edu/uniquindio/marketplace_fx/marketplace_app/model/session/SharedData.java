package co.edu.uniquindio.marketplace_fx.marketplace_app.model.session;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SharedData {
    private Map<String, Object> sharedObjects;
    private List<String> notifications;

    public SharedData() {
        sharedObjects = new ConcurrentHashMap<>();
        notifications = Collections.synchronizedList(new ArrayList<>());
    }
    // Obtener un valor con un valor predeterminado
    public Object getOrDefault(String key, Object defaultValue) {
        return sharedObjects.getOrDefault(key, defaultValue);
    }
    public void put(String key, Object value) {
        sharedObjects.put(key, value);
    }
    public Object get(String key) {
        return sharedObjects.get(key);
    }
}
