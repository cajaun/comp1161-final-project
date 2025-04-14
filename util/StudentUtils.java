package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import models.Student;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StudentUtils {
    private static final Path path = Paths.get("data", "students.json");

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void saveStudents(List<Student> students) {
        try (Writer writer = new FileWriter(path.toString())) {
            GSON.toJson(students, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> loadStudents() {
        try (Reader reader = new FileReader(path.toString())) {
            Type studentListType = new TypeToken<List<Student>>() {
            }.getType();
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
