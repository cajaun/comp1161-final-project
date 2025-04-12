import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StudentUtils {
    private static final String FILE_PATH = "students.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void saveStudents(List<Student> students) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            GSON.toJson(students, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> loadStudents() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type studentListType = new TypeToken<List<Student>>() {}.getType();
            return GSON.fromJson(reader, studentListType);
        } catch (FileNotFoundException e) {
            // If file doesn't exist, return an empty list
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
