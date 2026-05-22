package persistence;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonStorage {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <T> List<T> readList(String filePath, Class<T> clazz) {
        try {
            File file = new File(filePath);

            if (!file.exists()) {
                createEmptyJsonFile(file);
                return new ArrayList<>();
            }
            if (file.length() == 0) {
                return new ArrayList<>();
            }
            FileReader reader = new FileReader(file);
            Type listType = TypeToken.getParameterized(List.class, clazz).getType();
            List<T> items = gson.fromJson(reader, listType);
            reader.close();

            if (items == null) {
                return new ArrayList<>();
            }
            return items;
        } 
        catch (Exception e) {
            System.out.println("Error reading JSON file: " + filePath);
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public static <T> void writeList(String filePath, List<T> items) {
        try {
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            FileWriter writer = new FileWriter(file);
            gson.toJson(items, writer);
            writer.close();
        } 
        catch (Exception e) {
            System.out.println("Error writing JSON file: " + filePath);
            System.out.println(e.getMessage());
        }
    }

    private static void createEmptyJsonFile(File file) throws Exception {
        File parent = file.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        FileWriter writer = new FileWriter(file);
        writer.write("[]");
        writer.close();
    }
}