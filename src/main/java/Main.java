import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {

        String jsonPath = "C:\\Users\\Popa\\IdeaProjects\\BigDataProcessing\\src\\main\\resources\\myJson";
        Gson parser = new Gson();

        try {
            JsonReader json = new JsonReader(new FileReader(jsonPath));

            Group group = parser.fromJson(json, Group.class);
            System.out.println(group.students.get(0).name);

        } catch (FileNotFoundException e) {
            System.out.println("Json file reading error: \n" + e.getMessage());
        }

    }
}
