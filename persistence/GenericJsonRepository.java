package persistence;
import domain.Identifiable;
import java.util.List;

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

    public T findById(int id) {
        List<T> items = findAll();

        for (T item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void save(T item) {
        List<T> items = findAll();

        if (item.getId() == 0) {
            item.setId(generateNextId(items));
            items.add(item);
        } 
        else {
            boolean updated = false;

            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getId() == item.getId()) {
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

    public void deleteById(int id) {
        List<T> items = findAll();
        items.removeIf(item -> item.getId() == id);
        JsonStorage.writeList(filePath, items);
    }

    public boolean existsById(int id) {
        return findById(id) != null;
    }

    private int generateNextId(List<T> items) {
        int maxId = 0;

        for (T item : items) {
            if (item.getId() > maxId) {
                maxId = item.getId();
            }
        }
        return maxId + 1;
    }
}