package util;

import org.json.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CourseLoader {
    public static List<String> loadCourses(String filePath) {
        List<String> courseList = new ArrayList<>();
        try {
            JSONTokener tokener = new JSONTokener(new FileReader(filePath));
            JSONObject jsonObject = new JSONObject(tokener);

            if (jsonObject.has("courseCodes")) {
                JSONArray array = jsonObject.getJSONArray("courseCodes");
                for (int i = 0; i < array.length(); i++) {
                    String course = array.optString(i, null);
                    if (course != null) {
                        courseList.add(course);
                    }
                }
            } else {
                throw new JSONException("'courseCodes' field is missing.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseList;
    }
}
