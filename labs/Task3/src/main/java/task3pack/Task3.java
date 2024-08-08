package task3pack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task3 {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Должно быть передано 3 аргумента");
            System.exit(1);
        }

        String testsPath = args[0];
        String valuePath = args[1];
        String reportPath = args[2];

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            // Парсинг JSON файлов как деревья (JsonObject)
            JsonObject testsJson = JsonParser.parseReader(new FileReader(testsPath)).getAsJsonObject();
            JsonObject valuesJson = JsonParser.parseReader(new FileReader(valuePath)).getAsJsonObject();

            // Получение списка значений из values.json
            for (JsonElement valueElement : valuesJson.getAsJsonArray("values")) {
                JsonObject valueObject = valueElement.getAsJsonObject();
                int id = valueObject.get("id").getAsInt();
                String value = valueObject.get("value").getAsString();

                // Обновление значений в tests.json
                updateValues(testsJson.getAsJsonArray("tests"), id, value);
            }

            // Запись обновленного JSON в файл отчета
            try (FileWriter writer = new FileWriter(reportPath)) {
                gson.toJson(testsJson, writer);
            }
            System.out.println("Отчет успешно составлен!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для обновления значений в JSON
    private static void updateValues(JsonElement tests, int id, String value) {
        for (JsonElement testElement : tests.getAsJsonArray()) {
            JsonObject testObject = testElement.getAsJsonObject();
            if (testObject.get("id").getAsInt() == id) {
                testObject.addProperty("value", value);
            }
            // Проверка на наличие вложенных тестов
            if (testObject.has("values")) {
                updateValues(testObject.get("values"), id, value);
            }
        }
    }
}