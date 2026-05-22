package persistence;
import domain.Identifiable;
import java.util.List;
import java.util.UUID;

public class GenericJsonRepository<T extends Identifiable> {
    private final String filePath;
    private final Class<T> clazz;

    public GenericJsonRepository(String filePath, Class<T> clazz) {
        this.filePath = filePath;
        this.clazz = clazz;
    }

    public List<T> findAll() {
        return JsonStorage.readList(filePath, clazz);
    }

    public T findById(String id) {
        List<T> items = findAll();

        for (T item : items) {
            if (item.getId() != null && item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public void save(T item) {
        List<T> items = findAll();

        if (item.getId() == null || item.getId().isEmpty()) {
            item.setId(UUID.randomUUID().toString());
            items.add(item);
        } 
        else {
            boolean updated = false;

            for (int i = 0; i < items.size(); i++) {
                if (item.getId().equals(items.get(i).getId())) {
                    items.set(i, item);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                items.add(item);
            }
        }
        JsonStorage.writeList(filePath, items);
    }

    public void deleteById(String id) {
        List<T> items = findAll();
        items.removeIf(item -> item.getId() != null && item.getId().equals(id));
        JsonStorage.writeList(filePath, items);
    }

    public boolean existsById(String id) {
        return findById(id) != null;
    }
}